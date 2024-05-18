package co.istad.lms.features.minio;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinioStorageService {
    void uploadFile(MultipartFile file, String objectName) throws Exception;

    InputStream getFile(String objectName) throws Exception;

    void deleteFile(String objectName) throws Exception;

    String getFileContentType(String objectName) throws Exception;
}
