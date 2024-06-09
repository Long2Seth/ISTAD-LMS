package co.istad.lms.features.minio;


import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static co.istad.lms.features.media.MediaServiceImpl.getContentType;

@Service
@RequiredArgsConstructor
public class MinioStorageServiceImpl implements MinioStorageService {

    private final MinioClient minioClient;

    private final UrlCache urlCache = new UrlCache();

    private final Lock lock = new ReentrantLock();

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${url.expiry}")
    private int urlExpiryTime;

    @Value("${app.domain}")
    private String domain;

    @Override
    public void uploadFile(MultipartFile file, String objectName) throws Exception {

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw new Exception("File upload failed: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream getFile(String objectName) throws Exception {

        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(String objectName) throws Exception {

        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean doesObjectExist(String fileName) {
        try {
            //check fileName is existed or not
            if(fileName==null|| fileName.trim().isEmpty()){
                return false;
            }

            //check content type for file
            String contentType = getContentType(fileName);
            if (contentType == null || !contentType.trim().isEmpty()) {
                return false;
            }

            //get folder name
            String folderName = contentType.split("/")[0];

            //get object name to request to minio
            String objectName = folderName + "/" + fileName;

            //check file in minio
            return checkObjectExist(objectName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format( "File = %s has not available",fileName));
        }
    }

    @Override
    public String getUrl(String fileName) {
        if (fileName != null && !fileName.trim().isEmpty()) {
            String url = "";
            try {
                String contentType = getContentType(fileName);
                if (contentType == null||contentType.trim().isEmpty()) {
                    return null;
                }

                String folderName = contentType.split("/")[0];

                String objectName = folderName + "/" + fileName;

                //generate file from minio
                return getPreSignedUrlAsync(objectName).join();

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not generate image url");
            }
        }
        return null;
    }

    @Override
    public String getShortenedUrl(String fileName) {
        return String.format("%s/api/v1/files/%s", domain, fileName);
    }


    @Override
    public String extractExtension(String mediaName) {

        int lastDotIndex = mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex + 1);
    }

    private CompletableFuture<String> getPreSignedUrlAsync(String objectName) {

        return CompletableFuture.supplyAsync(() -> {
            String cachedUrl = urlCache.getCachedUrl(objectName);
            if (cachedUrl != null) {
                return cachedUrl;
            }

            lock.lock();
            try {
                // Double-check the cache to avoid redundant URL generation
                cachedUrl = urlCache.getCachedUrl(objectName);
                if (cachedUrl != null) {
                    return cachedUrl;
                }

                try {
                    String url = minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(bucketName)
                                    .object(objectName)
                                    .expiry(urlExpiryTime*60*60) // URL expiry time in seconds (e.g., 1 week)
                                    .build()
                    );
                    urlCache.cacheUrl(objectName, url );
                    return url;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to generate pre-signed URL", e);
                }
            } finally {
                lock.unlock();
            }
        });
    }

    private boolean checkObjectExist(String objectName) {

        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            return true;
        } catch (ErrorResponseException e) {

            //return false if object doesn't exist
            if ("NoSuchKey".equals(e.errorResponse().code())) {
                return false;
            }
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException ignored) {
        }
        return true;
    }

}
