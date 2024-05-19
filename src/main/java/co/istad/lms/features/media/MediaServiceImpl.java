package co.istad.lms.features.media;

import co.istad.lms.features.minio.MinioStorageService;
import co.istad.lms.features.media.dto.MediaResponse;
import co.istad.lms.utils.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MinioStorageService minioService;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse uploadSingle(MultipartFile file, String folderName) throws Exception {
        String newName = UUID.randomUUID().toString();
        String extension = MediaUtil.extractExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String objectName = folderName + "/" + newName + "." + extension;
        String url = minioService.getPreSignedUrl(objectName);
        try {
            minioService.uploadFile(file, objectName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return MediaResponse.builder()
                .name(newName + "." + extension)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(url)
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        List<MediaResponse> mediaResponses = new ArrayList<>();
        files.forEach(file -> {
            MediaResponse mediaResponse = null;
            try {
                mediaResponse = this.uploadSingle(file, folderName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {
        String objectName = folderName + "/" + mediaName;
        try {
            String contentType = minioService.getFileContentType(objectName);
            String url = minioService.getPreSignedUrl(objectName);
            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(contentType)
                    .extension(MediaUtil.extractExtension(mediaName))
                    .uri(url)
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        String objectName = folderName + "/" + mediaName;
        try {
            minioService.deleteFile(objectName);
            return MediaResponse.builder()
                    .name(mediaName)
                    .extension(MediaUtil.extractExtension(mediaName))
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Resource downloadMediaByName(String mediaName, String folderName) {
        String objectName = folderName + "/" + mediaName;
        try {
            InputStream inputStream = minioService.getFile(objectName);
            Path tempFile = Files.createTempFile("minio", mediaName);
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return new UrlResource(tempFile.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media has not been found!");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
