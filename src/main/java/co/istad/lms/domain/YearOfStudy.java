package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String UUID;

    @Column(name = "year" , nullable = false)
    private Integer year;

    @Column(name = "semester" , nullable = false)
    private Integer semester;


    @Column(name = "study_program_id" , nullable = false)
    private Long studyProgramId;

    @Column(name = "subject_id" , nullable = false)
    private Long subjectId;



}
