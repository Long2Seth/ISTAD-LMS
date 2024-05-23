package co.istad.lms.config.jpa;

import co.istad.lms.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EntityAuditorAware implements AuditorAware<String> {

    private final HttpServletRequest request;

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {

        String requestURI = request.getRequestURI();

        String method = request.getMethod();

        // Check if the request URI matches the registration endpoint with POST method
        if (("POST".equalsIgnoreCase(method) && requestURI.equals("/api/v1/auth/register")) ||
                ("POST".equalsIgnoreCase(method) && requestURI.equals("/api/v1/admissions"))) {
            return Optional.of("unknown");
        }
        //get authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //validate unauthorized
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please authorize");
        }

        Object principal = authentication.getPrincipal();

        //validate user is valid or not
        if (principal instanceof CustomUserDetails user) {
            return Optional.of(user.getUsername());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user details");
        }
    }
}
