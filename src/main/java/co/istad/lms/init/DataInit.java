package co.istad.lms.init;


import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void initRole() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (authorityRepository.count() < 1) {

            Authority userRead = new Authority();
            userRead.setAuthorityName("user:read");
            Authority userWrite = new Authority();
            userWrite.setAuthorityName("user:write");
            Authority transactionRead = new Authority();
            transactionRead.setAuthorityName("transaction:read");
            Authority transactionWrite = new Authority();
            transactionWrite.setAuthorityName("transaction:write");
            Authority accountRead = new Authority();
            accountRead.setAuthorityName("account:read");
            Authority accountWrite = new Authority();
            accountWrite.setAuthorityName("account:write");
            Authority accountTypeRead = new Authority();
            accountTypeRead.setAuthorityName("accountType:read");
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setAuthorityName("accountType:write");

            authorityRepository.saveAll(List.of(userRead, userWrite, transactionRead, transactionWrite, accountRead, accountWrite, accountTypeRead, accountTypeWrite));
        }

    }


}
