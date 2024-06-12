package co.istad.lms.features.media;

import co.istad.lms.features.media.dto.MediaResponse;
import co.istad.lms.features.media.dto.MediaViewResponse;
import io.minio.errors.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MediaService {

    /**
     * Uploads a single media file.
     *
     * @param file the media file to upload
     *             file type can be video, image, document
     * @return the response object containing the uploaded media details
     * @throws Exception if an error occurs during the upload
     */
    MediaResponse uploadSingle(MultipartFile file) throws Exception;

    /**
     * Uploads multiple media files.
     *
     * @param files the list of media files to upload
     * @return the list of response objects containing the uploaded media details
     */
    List<MediaResponse> uploadMultiple(List<MultipartFile> files);

    /**
     * Loads media details by its name.
     *
     * @param mediaName the name with extension of the media
     * @return the response object containing the media details
     */
    MediaResponse loadMediaByName(String mediaName);

    /**
     * Deletes media by its name.
     *
     * @param mediaName the name with extension of the media to delete
     * @return the response object containing the details of the deleted media
     */
    MediaResponse deleteMediaByName(String mediaName);

    /**
     * Downloads media by its name.
     *
     * @param mediaName the name with extension of the media to download
     * @return the resource object containing the media file
     */
    Resource downloadMediaByName(String mediaName);


    MediaViewResponse viewByFileName(String fileName) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, io.minio.errors.ServerException;

    String getUrl(String fileName);
}
