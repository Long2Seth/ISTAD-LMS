package co.istad.lms.features.user;




import co.istad.lms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrUsername(String email, String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);

    Optional<User> findByUsername(String username);


}
