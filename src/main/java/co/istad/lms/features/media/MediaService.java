package co.istad.lms.features.media;

import co.istad.lms.features.media.dto.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    MediaResponse uploadSingle(MultipartFile file, String folderName) throws Exception;

    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);

    MediaResponse loadMediaByName(String mediaName, String folderName);

    MediaResponse deleteMediaByName(String mediaName, String folderName);

    Resource downloadMediaByName(String mediaName, String folderName);

}
