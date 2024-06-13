package co.istad.lms.features.user;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrUsername(String email, String username);


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByIsChangePassword(boolean isChangePassword);

    boolean existsByEmailOrUsernameAndUuidNot(String email, String username, String uuid);

    Optional<User> findByEmailOrUsername(String email, String username);


    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);


    @Query("SELECT u FROM User u WHERE u.id IN (SELECT a.user.id FROM Admin a)")
    Page<User> findAllUsersWithAdminRole(Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Student s WHERE s.user.id = :userId")
    boolean existsByUserIdAndStudent(Long userId);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Instructor i WHERE i.user.id = :userId")
    boolean existsByUserIdAndInstructor(Long userId);

    @Query("SELECT CASE WHEN COUNT(st) > 0 THEN TRUE ELSE FALSE END FROM Staff st WHERE st.user.id = :userId")
    boolean existsByUserIdAndStaff(Long userId);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Academic a WHERE a.user.id = :userId")
    boolean existsByUserIdAndAcademic(Long userId);

    @Query("SELECT CASE WHEN COUNT(ad) > 0 THEN TRUE ELSE FALSE END FROM Admin ad WHERE ad.user.id = :userId")
    boolean existsByUserIdAndAdmin(Long userId);


}
