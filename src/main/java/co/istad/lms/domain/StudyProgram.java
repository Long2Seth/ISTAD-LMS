package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "study_programs")
@Entity
public class StudyProgram extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String alias;

    @Column( length = 50,nullable = false)
    private String studyProgramName;

    private String description;

    private String logo;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "degree_alias",nullable = false)
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "faculty_alias",nullable = false)
    private Faculty faculty;

    @ManyToMany
    @JoinTable(
            name = "study_programs_subjects",
            joinColumns = @JoinColumn(name = "study_program_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

}
