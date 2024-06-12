package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "student_admissions")
@Entity
public class StudentAdmission extends Auditable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 100)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String nameEn;

    @Column(nullable = false, length = 50)
    private String nameKh;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String highSchool;

    @Column(length = 50)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(columnDefinition = "TEXT")
    private String pob;

    @Column(length = 10)
    private String bacIiGrade;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String guardianContact;

    @Column(length = 50)
    private String guardianRelationShip;

    @Column(columnDefinition = "TEXT")
    private String knownIstad;

    @Column(columnDefinition = "TEXT")
    private String identity;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(length = 100)
    private String studentUuid;


    @ManyToOne
    @JoinColumn(name = "shift_id",nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "study_program_id",nullable = false)
    private StudyProgram studyProgram;

    @ManyToOne
    @JoinColumn(name = "degree_id",nullable = false)
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "admission_id")
    private Admission admission;

    @Column(nullable = false)
    private Boolean isDeleted ;

    @Column(nullable = false)
    private boolean isStudent;

}
