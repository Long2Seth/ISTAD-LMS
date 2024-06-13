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

    @Column(nullable = false, length = 100, unique = true)
    private String alias;

    @Column(nullable = false)
    private String studyProgramName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String logo;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Boolean isDraft;

    @ManyToOne
    @JoinColumn(name = "degree_alias",nullable = false)
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "faculty_alias",nullable = false)
    private Faculty faculty;


}
