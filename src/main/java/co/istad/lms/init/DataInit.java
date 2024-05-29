package co.istad.lms.init;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeAuthorities() {
        if (authorityRepository.count() < 44) {  // Total number of authorities to be initialized
            List<String> authorityNames = List.of(
                    "faculty:read",
                    "faculty:write",
                    "faculty:update",
                    "faculty:delete",
                    "generation:read",
                    "generation:write",
                    "generation:update",
                    "generation:delete",
                    "assessment:read",
                    "assessment:write",
                    "assessment:update",
                    "assessment:delete",
                    "class:read",
                    "class:write",
                    "class:update",
                    "class:delete",
                    "course:read",
                    "course:write",
                    "course:update",
                    "course:delete",
                    "session:read",
                    "session:write",
                    "session:update",
                    "session:delete",
                    "material:read",
                    "material:write",
                    "material:update",
                    "material:delete",
                    "admission:read",
                    "admission:write",
                    "admission:update",
                    "admission:delete",
                    "payment:read",
                    "payment:write",
                    "payment:update",
                    "payment:delete",
                    "user:read",
                    "user:write",
                    "user:update",
                    "user:delete",
                    "admin:control"
            );

            List<Authority> authorities = authorityNames.stream()
                    .map(this::createAuthority)
                    .toList();

            authorityRepository.saveAll(authorities);
        }
    }

    private Authority createAuthority(String authorityName) {
        Authority authority = new Authority();
        authority.setAuthorityName(authorityName);
        authority.setUuid(UUID.randomUUID().toString());
        return authority;
    }

    @PostConstruct
    void initUser() {
        System.setProperty("dataInit.running", "true");
        try {
            // Auto generate user (USER, CUSTOMER, STAFF, ADMIN)
            if (userRepository.count() < 1) {
                User user = new User();
                user.setUuid(UUID.randomUUID().toString());
                user.setNameEn("admin");
                user.setNameKh("អេតមីន");
                user.setUsername("admin");
                user.setEmail("admin@gmail.com");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setGender("male");
                user.setProfileImage("https://newogle.com");
                user.setPhoneNumber("0123456789");
                user.setCityOrProvince("Phnom Penh");
                user.setKhanOrDistrict("Dangkao");
                user.setSangkatOrCommune("Dangkao");
                user.setVillageOrPhum("Dangkao");
                user.setStreet("Dangkao");
                user.setIsBlocked(false);
                user.setIsDeleted(false);

                // BirthPlace
                BirthPlace birthPlace = new BirthPlace();
                birthPlace.setCityOrProvince("Phnom Penh");
                birthPlace.setKhanOrDistrict("Dangkao");
                birthPlace.setSangkatOrCommune("Dangkao");
                birthPlace.setVillageOrPhum("Dangkao");
                birthPlace.setStreet("Dangkao");
                user.setBirthPlace(birthPlace);

                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);

                // Authorities
                List<Authority> authorities = authorityRepository.findAll();
                System.out.println(authorities);
                user.setAuthorities(authorities);

                userRepository.save(user);
            }
        } finally {
            System.clearProperty("dataInit.running");
        }
    }
}
