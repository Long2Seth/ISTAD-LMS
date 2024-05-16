package co.istad.istadlms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false , length = 50)
    private String roleName;

    private String description;

    //Relationship with user

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}