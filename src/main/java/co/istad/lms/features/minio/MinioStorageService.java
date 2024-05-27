package co.istad.lms.features.minio;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinioStorageService {

    /**
     * Uploads a file to the Minio storage.
     *
     * @param file       the file to upload
     * @param objectName the name of the object in the storage
     * @throws Exception if an error occurs during the upload
     */
    void uploadFile(MultipartFile file, String objectName) throws Exception;

    /**
     * Retrieves a file from the Minio storage as an InputStream.
     *
     * @param objectName the name of the object in the storage
     * @return the InputStream of the retrieved file
     * @throws Exception if an error occurs during the retrieval
     */
    InputStream getFile(String objectName) throws Exception;

    /**
     * Deletes a file from the Minio storage.
     *
     * @param objectName the name of the object in the storage
     * @throws Exception if an error occurs during the deletion
     */
    void deleteFile(String objectName) throws Exception;

    /**
     * Retrieves the content type of a file in the Minio storage.
     *
     * @param objectName the name of the object in the storage
     * @return the content type of the file
     * @throws Exception if an error occurs during the retrieval
     */
    String getFileContentType(String objectName) throws Exception;

    /**
     * Generates a pre-signed URL for accessing a file in the Minio storage.
     *
     * @param objectName the name of the object in the storage
     * @return the pre-signed URL for accessing the file
     * @throws Exception if an error occurs during the URL generation
     */
    String getPreSignedUrl(String objectName) throws Exception;
}
