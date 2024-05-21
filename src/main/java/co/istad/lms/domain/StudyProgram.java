package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "study_programs")
@Entity
public class StudyProgram extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "alias", unique = true)
    private String alias;

    @Column(nullable = false, length = 50, name = "syudy_program_name")
    private String studyProgramName;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "faculty_id")
    private Long facultyId;

}
