package co.istad.lms.features.minio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class MinioController {

    private final MinioStorageService minioStorageService;

    @GetMapping("/{fileName:.+}")
    @ResponseStatus(HttpStatus.FOUND)
    public RedirectView getFile(@PathVariable String fileName) {
        String url = minioStorageService.getUrl(fileName);
        if (url == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
        return new RedirectView(url);
    }
}
