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
@Table(name = "staffs")
@Entity
public class Staff {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true)
    private String uuid;



    @Column(length = 50)
    private String highSchool;



    private LocalDate highSchoolGraduationDate;



    private LocalDate degreeGraduationDate;




    @Column(length = 50)
    private String studyAtUniversityOrInstitution;




    @Column(length = 50)
    private String experienceAtWorkingPlace;




    private Integer experienceYear;





    @Column(columnDefinition = "TEXT")
    private String position;




    @ElementCollection
    @CollectionTable(name = "staff_degrees", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "staff", length = 50)
    private Set<String> degree;



    @ElementCollection
    @CollectionTable(name = "staff_majors", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "staff", length = 50)
    private Set<String> major;



    @Column(columnDefinition = "TEXT")
    private String linkGit;



    @Column(columnDefinition = "TEXT")
    private String linkLinkedin;



    @Column(columnDefinition = "TEXT")
    private String linkTelegram;



    @Column(columnDefinition = "TEXT")
    private String uploadCv;



    @Column(columnDefinition = "TEXT")
    private String identityCard;



    @Column(columnDefinition = "TEXT")
    private String bio;



    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
