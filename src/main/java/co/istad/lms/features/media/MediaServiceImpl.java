package co.istad.lms.features.media;

import co.istad.lms.features.minio.MinioStorageService;
import co.istad.lms.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public MediaResponse uploadSingle(MultipartFile file) throws Exception {

        String contentType = file.getContentType();
        if (contentType == null || !(contentType.startsWith("image/") ||
                contentType.startsWith("video/") ||
                contentType.equals("application/pdf") ||
                contentType.equals("application/vnd.ms-powerpoint") ||
                contentType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation") ||
                contentType.equals("application/msword") ||
                contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                contentType.equals("text/plain"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported file type.");
        }

        String folderName = contentType.split("/")[0];

        String newName = UUID.randomUUID().toString();

        String extension = minioService.extractExtension(Objects.requireNonNull(file.getOriginalFilename()));

        String objectName = folderName + "/" + newName + "." + extension;

        String url = minioService.getUrl(newName+"."+extension);

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
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files) {

        List<MediaResponse> mediaResponses = new ArrayList<>();
        files.forEach(file -> {
            MediaResponse mediaResponse = null;
            try {
                mediaResponse = this.uploadSingle(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            mediaResponses.add(mediaResponse);
        });

        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName) {

        try {
            String contentType = getContentType(mediaName);
            String url = minioService.getUrl(mediaName);


            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(contentType)
                    .extension(minioService.extractExtension(mediaName))
                    .uri(url)
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName) {

        try {
            String contentType = getContentType(mediaName);

            String folderName = contentType.split("/")[0];

            String objectName = folderName + "/" + mediaName;

            minioService.deleteFile(objectName);

            return MediaResponse.builder()
                    .name(mediaName)
                    .extension(minioService.extractExtension(mediaName))
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                    .build();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Resource downloadMediaByName(String mediaName) {

        try {
            String contentType = getContentType(mediaName);

            String folderName = contentType.split("/")[0];

            String objectName = folderName + "/" + mediaName;

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

    public static String getContentType(String fileName) {
        Path path = Paths.get(fileName);
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            return "Unknown type";
        }
    }
}
