package co.istad.lms.domain.roles;




import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "instructors")
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String uuid;

    @Column(length = 50)
    String highSchool;

    LocalDate highSchoolGraduationDate;

    @Column(length = 50)
    String degree; // Bachelor, Master, Doctor

    LocalDate degreeGraduationDate;

    @Column(length = 50)
    String major;

    @Column(length = 50)
    String studyAtUniversityOrInstitution;

    @Column(length = 50)
    String experienceAtWorkingPlace;

    Integer experienceYear; // experience compare per year

    private boolean status;

    private boolean isDeleted;

    @OneToOne
    private User user;

}
