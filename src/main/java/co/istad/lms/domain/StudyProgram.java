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

    @Column(nullable = false, length = 50, unique = true)
    private String alias;

    @Column(nullable = false, length = 50)
    private String studyProgramName;

    private String description;

    private String logo;

    @Column(unique = true)
    private Boolean isDeleted;

    @Column(nullable = false)
    private String facultyAlias;

}
