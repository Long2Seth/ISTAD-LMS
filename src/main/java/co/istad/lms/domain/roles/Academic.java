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
@Table(name = "academics")
@Entity
public class Academic {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    @Column(unique = true)
    private String uuid;




    @Column(length = 50)
    private String highSchool;





    private LocalDate highSchoolGraduationDate;





    private LocalDate degreeGraduationDate;





    @ElementCollection
    @CollectionTable(name = "academic_educations", joinColumns = @JoinColumn(name = "academic_id"))
    @Column(name = "educations", length = 50)
    private Set<String> educations;




    @ElementCollection
    @CollectionTable(name = "academic_skills", joinColumns = @JoinColumn(name = "academic_id"))
    @Column(name = "skills", length = 50)
    private Set<String> skills;





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
    private String identityCard;





    @Column(columnDefinition = "TEXT")
    private String bio;




    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;




}
