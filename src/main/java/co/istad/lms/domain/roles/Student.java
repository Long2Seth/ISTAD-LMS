package co.istad.lms.domain.roles;

import co.istad.lms.domain.Class;
import co.istad.lms.domain.Course;
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
@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true)
    private String uuid;




    @Column(nullable = false)
    private String highSchool;




    @Column(length = 50)
    private Integer familyPhoneNumber;




    @Column(length = 10)
    private String bacIiGrade;


    @Column()
    private String diplomaSession;



    @Column(length = 50)
    private String guardianContact;



    @Column(length = 50)
    private String guardianRelationShip;



    @Column(columnDefinition = "TEXT")
    private String knownIstad;



    @Column(columnDefinition = "TEXT")
    private String identity; // image


    @Column(columnDefinition = "TEXT")
    private String highSchoolCertificate; // image


    @Column(columnDefinition = "TEXT")
    private String vocationTrainingCertificate;// image


    @Column(columnDefinition = "TEXT")
    private String anyValuableCertificate; // image



    @Column(columnDefinition = "TEXT")
    private String biography;



    private Boolean active;
    private Boolean hiatus;
    private Boolean drop;
    private Boolean disable; // delete source of student



    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Class> classes;



    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;



}
