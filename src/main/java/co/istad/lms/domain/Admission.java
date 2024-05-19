package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "admissions")
@Entity
public class Admission {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, length = 50,unique = true)
    private String uuid;

    @Column(name = "nameEn", nullable = false, length = 50)
    private String nameEn;

    @Column(name = "nameKh", nullable = false, length = 50)
    private String nameKh;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "family_phone_number", length = 50)
    private String familyPhoneNumber;

    @Column(name = "biography")
    private String biography;

    @Column(name = "shift_id", nullable = false)
    private String shift;

    @Column(name = "study_program_id", nullable = false)
    private String studyProgram;

    @Column(name = "degree_id", nullable = false)
    private String degreeUuid;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private Timestamp modifiedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted=false;


}
