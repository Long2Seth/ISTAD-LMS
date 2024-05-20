package co.istad.lms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false , length = 50)
    private String roleName;

    private String description;

    //Relationship with user

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    //Relationship with authority
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName; // ROLE_ADMIN, ROLE_STAFF
    }
}