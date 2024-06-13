package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Academic;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String nameEn;

    @Column(nullable = false, length = 50)
    private String nameKh;

    @Column(nullable = false, length = 50,unique = true)
    private String username;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(name = "birth_of_date")
    private LocalDate dob;

    @Column(nullable = false, length = 100)
    private String email;


    private String password;

    private String rawPassword;

    private String profileImage;

    @Column(length = 20)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String currentAddress;

    @Column(name = "birth_place", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private BirthPlace birthPlace;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;


    private Boolean isDeleted;
    private Boolean status;
    private Boolean isChangePassword;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    private Set<Authority> authorities;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Instructor instructor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Academic academic;

}
