package co.istad.lms.domain.roles;

import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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


    @Column(columnDefinition = "TEXT")
    private String position;


    @Column(length = 50)
    private String highSchool;


    private LocalDate highSchoolGraduationDate;


    @ElementCollection
    @CollectionTable(name = "instructor_degrees", joinColumns = @JoinColumn(name = "instructor_id"))
    @Column(name = "degree", length = 50)
    private Set<String> degree;


    private LocalDate degreeGraduationDate;


    @ElementCollection
    @CollectionTable(name = "instructor_majors", joinColumns = @JoinColumn(name = "instructor_id"))
    @Column(name = "major", length = 50)
    private Set<String> major;


    @Column(length = 50)
    private String studyAtUniversityOrInstitution;


    @Column(length = 50)
    private String experienceAtWorkingPlace;


    private Integer experienceYear;


    @Column(columnDefinition = "TEXT")
    private String linkGit;


    @Column(columnDefinition = "TEXT")
    private String linkLinkedin;


    @Column(columnDefinition = "TEXT")
    private String linkTelegram;


    @Column(columnDefinition = "TEXT")
    private String uploadCv;


    @Column(columnDefinition = "TEXT")
    private String identity;


    @Column(columnDefinition = "TEXT")
    private String bio;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
