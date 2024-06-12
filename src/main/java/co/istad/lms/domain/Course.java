package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
@Entity
public class Course extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 100)
    private String uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isDraft;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Boolean isStarted;

    private LocalDate courseStart;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class oneClass;

    @ManyToMany(mappedBy = "courses")
    Set<Student> students;


}
