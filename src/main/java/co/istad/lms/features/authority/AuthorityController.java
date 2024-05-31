package co.istad.lms.features.authority;

import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping
    public Page<AuthorityResponse> getPageAuthorities(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit) {
        return authorityService.findAll(page, limit);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthorityResponse createAuthority(@RequestBody AuthorityRequest authorityRequest) {
       return authorityService.create(authorityRequest);
    }

    @GetMapping("/{uuid}")
    public AuthorityResponse getAuthorityById(@PathVariable String uuid) {
        return authorityService.findById(uuid);
    }

    @PutMapping("/{uuid}")
    public AuthorityResponse updateAuthority(@PathVariable String uuid, @RequestBody AuthorityRequest authorityRequest) {
        return authorityService.update(uuid, authorityRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteAuthority(@PathVariable String uuid) {
        authorityService.delete(uuid);
    }
}
