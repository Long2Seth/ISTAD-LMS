package co.istad.lms.domain.roles;




import co.istad.lms.domain.Class;
import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String uuid;

    boolean status;

    boolean isDeleted;

    @OneToOne
    private User user;


    @ManyToMany
    @JoinTable(
            name = "students_classes",
            joinColumns = @JoinColumn( referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id" , referencedColumnName = "id")
    )
    private List<Class> classes;

}
