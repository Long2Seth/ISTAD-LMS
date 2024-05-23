package co.istad.lms.config.jpa;

import co.istad.lms.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EntityAuditorAware implements AuditorAware<String> {

    private final HttpServletRequest request;

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {

        // Skip authentication if the initialization is running
        if ("true".equals(System.getProperty("dataInit.running"))) {
            return Optional.of("initializer");
        }

        // Your existing authentication logic
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        //for admission that don't know username
        if ("POST".equalsIgnoreCase(method) && requestURI.equals("/api/v1/admissions")) {
            return Optional.of("unknown");
        }

        //for user to login
        if ("POST".equalsIgnoreCase(method) && requestURI.equals("/api/v1/auth/login")) {
            return Optional.of("user");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please authorize");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            return Optional.of(user.getUsername());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user details");
        }
    }
}
