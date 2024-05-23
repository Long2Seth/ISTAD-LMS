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
@Table(name = "year_of_studies")
@Entity
public class YearOfStudy extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false,unique = true)
    private String uuid;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;


    @ManyToOne
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

    @Column(  nullable = false)
    private String subjectAlias;



}
