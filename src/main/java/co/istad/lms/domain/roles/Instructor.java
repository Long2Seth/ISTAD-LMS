package co.istad.lms.domain.roles;

import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String uuid;

    @Column(length = 50)
    private String highSchool;

    private LocalDate highSchoolGraduationDate;

    @Column(length = 50)
    private String degree;

    private LocalDate degreeGraduationDate;

    @Column(length = 50)
    private String major;

    @Column(length = 50)
    private String studyAtUniversityOrInstitution;

    @Column(length = 50)
    private String experienceAtWorkingPlace;

    private Integer experienceYear;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
