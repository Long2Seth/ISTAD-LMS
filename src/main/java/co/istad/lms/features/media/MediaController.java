package co.istad.lms.features.media;

import co.istad.lms.features.media.dto.MediaResponse;
import co.istad.lms.features.media.dto.MediaViewResponse;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileView;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload-single", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyAuthority('material:write')")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) throws Exception {

        return mediaService.uploadSingle(file);
    }


        @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload-multiple", consumes = "multipart/form-data")
    @PreAuthorize("hasAnyAuthority('material:write')")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files) {
        return mediaService.uploadMultiple(files);
    }

    @GetMapping("/{mediaName}")
    @PreAuthorize("hasAnyAuthority('material:read')")
    MediaResponse loadMediaByName(@PathVariable String mediaName) {
        return mediaService.loadMediaByName(mediaName);
    }


    @DeleteMapping("/{mediaName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('material:delete')")
    MediaResponse deleteMediaByName(@PathVariable String mediaName) {

        return mediaService.deleteMediaByName(mediaName);
    }

    @GetMapping(path = "/download/{mediaName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasAnyAuthority('material:read')")
    ResponseEntity<?> downloadMediaByName(@PathVariable String mediaName) {

        Resource resource = mediaService.downloadMediaByName(mediaName);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + mediaName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<InputStreamResource> viewByFileName(@PathVariable String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MediaViewResponse file = mediaService.viewByFileName(fileName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(file.contentType()))
                .contentLength(file.fileSize())
                .body(file.stream());
    }


}
