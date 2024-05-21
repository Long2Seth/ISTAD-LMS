package co.istad.lms.domain.roles;




import co.istad.lms.domain.Classes;
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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

    @ManyToMany
    @JoinTable(
            name = "student_classes",
            joinColumns = @JoinColumn(name = "student_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id" , referencedColumnName = "id")
    )
    private List<Classes> classes;

}
