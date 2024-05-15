package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "study_programs_tbl")
@Entity
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50 , name = "alias")
    private String alias;

    @Column(nullable = false , length = 50 , name = "syudy_program_name")
    private String studyProgramName;

    @Column(nullable = false , length = 50 , name = "description")
    private String description;

    @Column( name = "logo" )
    private String logo;

    @Column(name = "creared_by" , nullable = false , length = 50)
    private String createdBy;

    @Column(name = "created_date" , nullable = false)
    private LocalDate createdDate;

    @Column(name = "modified_by" , nullable = false , length = 50)
    private String modifiedBy;

    @Column(name = "modified_date" , nullable = false)
    private LocalDate modifiedDate;

    @Column( name = "faculty_id" )
    private Long facultyId;

}
