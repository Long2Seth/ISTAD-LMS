package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(nullable = false, unique = true,length = 100)
    private String uuid;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    Integer year;

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


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "classes_students",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "classes_year_of_studies",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "year_of_study_id")
    )
    private Set<YearOfStudy> yearOfStudies;

    @OneToMany(mappedBy = "oneClass", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    Set<Course> courses;
}
