package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "year_of_studies")
@Entity
public class YearOfStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID" , nullable = false , length = 50)
    private String UUID;

    @Column(name = "year" , nullable = false)
    private Integer year;

    @Column(name = "semester" , nullable = false)
    private Integer semester;

    @Column(name = "created_by" , nullable = false , length = 50)
    private String createdBy;

    @Column(name = "created_date" , nullable = false)
    private LocalDate createdDate;

    @Column(name = "modified_by" , nullable = false , length = 50)
    private String modifiedBy;

    @Column(name = "modified_date" , nullable = false)
    private LocalDate modifiedDate;

    @Column(name = "study_program_id" , nullable = false)
    private Long studyProgramId;

    @Column(name = "subject_id" , nullable = false)
    private Long subjectId;



}
