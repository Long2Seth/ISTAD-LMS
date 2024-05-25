package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "subjects")
@Entity
public class Subject extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true )
    private String alias;

    @Column( nullable = false )
    private String subjectName;

    private String description;

    private String subjectLogo;

    @Column(nullable = false)
    private Integer credit;

    @Column(nullable = false)
    private Integer duration;

    @ManyToMany
    @JoinTable(
            name = "subject_study_programs",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "study_program_id")
    )
    private Set<StudyProgram> studyPrograms;


}
