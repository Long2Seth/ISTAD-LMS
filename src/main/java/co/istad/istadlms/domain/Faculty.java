package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "faculties")
@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50 , name = "alias")
    private String alias;

    @Column(nullable = false , length = 50 , name = "name")
    private String name;

    @Column(nullable = false , length = 50 , name = "description")
    private String description;

    @Column( name = "address" , nullable = false , length = 50)
    private String address;


}
