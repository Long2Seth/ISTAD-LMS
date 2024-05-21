package co.istad.lms.config.jpa;

import co.istad.lms.domain.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import  org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Component
public class EntityAuditorAware implements AuditorAware<String> {

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        if(!authentication.isAuthenticated()){
            return Optional.empty();
        }
      UserDetails user=(UserDetails) authentication.getPrincipal();
        return Optional.of(user.getUsername());
    }
}
