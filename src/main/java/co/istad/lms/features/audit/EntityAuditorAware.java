package co.istad.lms.features.audit;

import co.istad.lms.domain.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntityAuditorAware implements AuditorAware<String> {
    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        assert authentication != null;
//        if(!authentication.isAuthenticated()){
//            return Optional.empty();
//        }
//        User user=(User) authentication.getPrincipal();
        return Optional.of("hello");
    }
}
