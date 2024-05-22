package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
    private String alias;

    @Column(nullable = false, length = 50)
    private String nameEn;

    @Column(nullable = false, length = 50)
    private String nameKh;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String profileImage;

    @Column(length = 20)
    private String phoneNumber;

    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String street;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Boolean isDeleted;
    private Boolean isBlocked;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    private List<Authority> authorities;

}
