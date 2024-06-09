package co.istad.lms.domain.roles;




import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.Class;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
@Entity
public class Student extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String uuid;

    boolean status;

    boolean isDeleted;

    @OneToOne
    private User user;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Class> classes;

    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses;
}
