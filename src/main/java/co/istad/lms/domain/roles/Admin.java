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
@Table(name = "admins")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(length = 50)
    private String highSchool;

    private LocalDate highSchoolGraduationDate;

    @Column(length = 50)
    private String degree; // Bachelor, Master, Doctor

    private LocalDate degreeGraduationDate;

    @Column(length = 50)
    private String major;

    @Column(length = 50)
    private String studyAtUniversityOrInstitution;

    @Column(length = 50)
    private String experienceAtWorkingPlace;

    private LocalDate experienceYear; // experience compare per year

    private boolean status;

    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
