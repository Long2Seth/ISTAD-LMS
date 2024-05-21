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

    @Column(name = "uuid" , nullable = false , length = 50)
    private String uuid;

    @Column(name = "year" , nullable = false)
    private Integer year;

    @Column(name = "semester" , nullable = false)
    private Integer semester;


    @ManyToOne
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

    @Column(name = "subject" , nullable = false)
    private String subjectId;



}
