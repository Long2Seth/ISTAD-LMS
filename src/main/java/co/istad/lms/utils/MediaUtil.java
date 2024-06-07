package co.istad.lms.utils;

import co.istad.lms.features.minio.MinioStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

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
            if(contentType==null){
                 return null;
            }
            String folderName = contentType.split("/")[0];
            String objectName = folderName + "/" + fileName;
            url = minioStorageService.getPreSignedUrl(objectName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not generate image url");
        }
        return url;
    }

}
