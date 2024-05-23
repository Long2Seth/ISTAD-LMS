package co.istad.lms.init;


import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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


}
