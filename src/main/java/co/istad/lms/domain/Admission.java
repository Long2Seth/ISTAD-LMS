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
@Table(name = "admissions")
@Entity
public class Admission extends Auditable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String nameEn;

    @Column(nullable = false, length = 50)
    private String nameKh;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 50)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dob;

    private String pob;

    @Column(length = 10)
    private String BacIiGrade;

    @Column(nullable = false, length = 20)
    private String gender;

    private String avatar;

    private String address;

    @Column(length = 50)
    private String familyPhoneNumber;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @ManyToOne
    @JoinColumn(name = "shift_id",nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "study_program_id",nullable = false)
    private StudyProgram studyProgram;

    @ManyToOne
    @JoinColumn(name = "degree_id",nullable = false)
    private Degree degree;

    @Column(nullable = false)
    private Boolean isDeleted = false;


}
