package co.istad.lms.features.user;




import co.istad.lms.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isBlocked = :status WHERE u.id = :userId")
    int updateBlockedStatusById(Long userId, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = :status WHERE u.id = :userId")
    int updateDeletedStatusById(Long userId, boolean status);
}
