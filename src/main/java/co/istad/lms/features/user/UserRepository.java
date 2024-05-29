package co.istad.lms.features.user;




import co.istad.lms.domain.User;
import co.istad.lms.domain.roles.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrUsername(String email, String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);


}
