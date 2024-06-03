package co.istad.lms.utils;

import co.istad.lms.features.minio.MinioStorageService;
import lombok.RequiredArgsConstructor;

import static co.istad.lms.features.media.MediaServiceImpl.getContentType;

public class MediaUtil {


    public static String extractExtension(String mediaName) {
        int lastDotIndex = mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex + 1);
    }

    public static String getUrl(String fileName,MinioStorageService minioStorageService) {
        String url = "";
        try {
            String contentType = getContentType(fileName);
            String folderName = contentType.split("/")[0];
            String objectName = folderName + "/" + fileName;
            url = minioStorageService.getPreSignedUrl(objectName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return url;
    }

}
