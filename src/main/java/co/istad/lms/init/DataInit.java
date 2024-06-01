package co.istad.lms.init;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.AdminRepository;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void initRole() {
        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (authorityRepository.count() <45) {
            List<String> authorityNames = List.of(
                    "faculty:read",
                    "faculty:write",
                    "faculty:update",
                    "faculty:delete",
                    "generation:read",
                    "generation:write",
                    "generation:update",
                    "generation:delete",
                    "shift:read",
                    "shift:write",
                    "shift:update",
                    "shift:delete",
                    "academic:read",
                    "academic:write",
                    "academic:update",
                    "academic:delete",
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
            user.setIsChangePassword(false);


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
            Set<Authority> authorities = new HashSet<>(authorityRepository.findAll());
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }


//    @PostConstruct
//    void initAdmin() {
//        if ( adminRepository.count()< 1) {
//            Optional<User> user = userRepository.findByUsername("admin");
//            Admin admin = new Admin();
//            admin.setUuid(UUID.randomUUID().toString());
//            admin.setHighSchool("High School");
//            admin.setHighSchoolGraduationDate(LocalDate.parse("2022-01-01"));
//            admin.setStudyAtUniversityOrInstitution("University");
//            admin.setMajor("Major");
//            admin.setDegreeGraduationDate(LocalDate.parse("2022-01-01"));
//            admin.setDegree("Bachelor");
//            admin.setExperienceAtWorkingPlace("ISTAD");
//            admin.setExperienceYear(5);
//            admin.setDeleted(false);
//            admin.setStatus(false);
//            admin.setUser(user.get());
//            adminRepository.save(admin);
//
//        }
//    }
}
