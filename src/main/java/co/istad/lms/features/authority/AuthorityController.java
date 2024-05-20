package co.istad.lms.features.authority;

import co.istad.lms.base.BasedResponse;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;


    @GetMapping("/all-authorities")
    public BasedResponse<List<AuthorityResponse>> getAllAuthorities() {
        return BasedResponse.<List<AuthorityResponse>>ok()
                .setPayload(authorityService.findAll());
    }

    @GetMapping("/all-authorities-page")
    public BasedResponse<Page<AuthorityResponse>> getPageAuthorities(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "2") int limit) {
        return BasedResponse.<Page<AuthorityResponse>>ok()
                .setPayload(authorityService.findAll(page, limit));
    }
}
