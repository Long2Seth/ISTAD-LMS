package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "year_of_studies", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"year", "semester", "study_program_id"})
})
@Entity
public class YearOfStudy extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false,unique = true,length = 100)
    private String uuid;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;


    @ManyToOne
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

    @ManyToMany
    @JoinTable(
            name = "year_of_subjects",
            joinColumns = @JoinColumn(name = "year_of_study_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;


    @ManyToMany
    @JoinTable(
            name = "yearofstudy_class",
            joinColumns = @JoinColumn(name = "yearofstudy_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private Set<Class> classes;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false)
    private Boolean isDraft;

    @OneToMany(mappedBy = "yearOfStudy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses;


}
