package co.istad.lms.features.authority;

import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;



    @GetMapping
    public Page <AuthorityResponse> getPageAuthorities(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int limit) {
        return authorityService.findAll(page, limit);
    }

    @PostMapping
    public AuthorityResponse createAuthority(@RequestBody AuthorityRequest authorityRequest) {
        return authorityService.create(authorityRequest);
    }

    @GetMapping("/{id}")
    public AuthorityResponse getAuthorityById(@PathVariable Long id) {
        return authorityService.findById(id);
    }

    @PutMapping("/{id}")
    public AuthorityResponse updateAuthority(@PathVariable Long id, @RequestBody AuthorityRequest authorityRequest) {
        return authorityService.update(id, authorityRequest);
    }

    @DeleteMapping("/{id}")
    public AuthorityResponse deleteAuthority(@PathVariable Long id) {
        return authorityService.delete(id);
    }
}
