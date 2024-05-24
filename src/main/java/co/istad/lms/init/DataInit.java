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
    void initRole() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (authorityRepository.count() < 1) {

            Authority userRead = new Authority();
            userRead.setAuthorityName("user:read");
            userRead.setUuid(UUID.randomUUID().toString());
            Authority userWrite = new Authority();
            userWrite.setAuthorityName("user:write");
            userWrite.setUuid(UUID.randomUUID().toString());
            Authority transactionRead = new Authority();
            transactionRead.setAuthorityName("transaction:read");
            transactionRead.setUuid(UUID.randomUUID().toString());
            Authority transactionWrite = new Authority();
            transactionWrite.setAuthorityName("transaction:write");
            transactionWrite.setUuid(UUID.randomUUID().toString());
            Authority accountRead = new Authority();
            accountRead.setAuthorityName("account:read");
            accountRead.setUuid(UUID.randomUUID().toString());
            Authority accountWrite = new Authority();
            accountWrite.setAuthorityName("account:write");
            accountWrite.setUuid(UUID.randomUUID().toString());
            Authority accountTypeRead = new Authority();
            accountTypeRead.setAuthorityName("accountType:read");
            accountTypeRead.setUuid(UUID.randomUUID().toString());
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setAuthorityName("accountType:write");
            accountTypeWrite.setUuid(UUID.randomUUID().toString());

            authorityRepository.saveAll(List.of(userRead, userWrite, transactionRead, transactionWrite, accountRead, accountWrite, accountTypeRead, accountTypeWrite));
        }

    }

    @PostConstruct
    void initUser() {
        // Auto generate user (USER, CUSTOMER, STAFF, ADMIN)
        if (userRepository.count() < 1) {
            User user = new User();
            user.setAlias("admin");
            user.setNameEn("Admin");
            user.setNameKh("Admin");
            user.setUsername("admin");
            user.setEmail("admin123@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setGender("male");
            user.setProfileImage("https://newogle.com");
            user.setPhoneNumber("0123456789");
            user.setCityOrProvince("Phnom Penh");
            user.setKhanOrDistrict("Dangkao");
            user.setSangkatOrCommune("Dangkao");
            user.setVillageOrPhum("Dangkao");
            user.setStreet("Dangkao");
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);

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

            // Authorities
            List<Authority>  authorities=  authorityRepository.findAll();
            System.out.println(authorities);
            user.setAuthorities(authorities);

            userRepository.save(user);
        }

        }


}
