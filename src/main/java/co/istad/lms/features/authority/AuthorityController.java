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
    public ResponseEntity<Page<AuthorityResponse>> getPageAuthorities(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit) {
        Page<AuthorityResponse> authorities = authorityService.findAll(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(authorities);
    }

    @PostMapping
    public ResponseEntity<AuthorityResponse> createAuthority(@RequestBody AuthorityRequest authorityRequest) {
        AuthorityResponse authorityResponse = authorityService.create(authorityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorityResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityResponse> getAuthorityById(@PathVariable Long id) {
        AuthorityResponse authorityResponse = authorityService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorityResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorityResponse> updateAuthority(@PathVariable Long id, @RequestBody AuthorityRequest authorityRequest) {
        AuthorityResponse authorityResponse = authorityService.update(id, authorityRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authorityResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorityResponse> deleteAuthority(@PathVariable Long id) {
        AuthorityResponse authorityResponse = authorityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(authorityResponse);
    }
}
