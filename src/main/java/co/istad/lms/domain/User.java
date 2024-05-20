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
    private Long id ;

    @Column(name = "alias",unique = true,nullable = false)
    private String alias;

    @Column(name = "name_en",nullable = false , length = 50)
    private String name_en;

    @Column(name = "name_kh",nullable = false , length = 50)
    private String name_kh;

    @Column(nullable = false , length = 50, name = "user_name")
    private String userName;

    @Column(nullable = false , length = 10)
    private String gender;

    @Column(nullable = false , length = 100)
    private String email;

    @Column(nullable = false , length = 100)
    private String password;

    @Column(nullable = false , name = "profile_image" )
    private String profileImage;

    @Column(name = "phone_nubmer",nullable = false , length = 20)
    private String phoneNumber;

    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String street;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Boolean isDeleted;
    private Boolean isBlocked;

}
