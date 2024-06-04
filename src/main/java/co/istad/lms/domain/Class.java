package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "classes")
@Entity
public class Class extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String className;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Boolean isDraft;

    @ManyToOne
    @JoinColumn(name = "instuctor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private StudyProgram studyProgram;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Generation generation;


    @OneToMany
    @JoinColumn(name = "class_id")
    private Set<Student> students;


}
