package co.istad.lms.init;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.Role;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.role.RoleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void initRole() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {

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

            Role user = new Role();
            user.setRoleName("USER");
            user.setAuthorities(List.of(
                    userRead, transactionRead,
                    accountRead, accountTypeRead
            ));

            Role customer = new Role();
            customer.setRoleName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite, transactionWrite,
                    accountWrite
            ));

            Role staff = new Role();
            staff.setRoleName("STAFF");
            staff.setAuthorities(List.of(
                    accountTypeWrite
            ));

            Role admin = new Role();
            admin.setRoleName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite, accountWrite,
                    accountTypeWrite
            ));

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }


}
