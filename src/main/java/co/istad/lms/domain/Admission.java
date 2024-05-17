package co.istad.lms.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "admissions")
@Entity
public class Admission {

    @Id
    @Column(name = "admission_id" , nullable = false , length = 50)
    private Long id;

    @Column(name = "name" , nullable = false , length = 50)
    private String name;

    @Column(name = "email" , nullable = false , length = 50)
    private String email;

    @Column(name = "phone" , nullable = false , length = 50)
    private String phone;

    @Column(name = "address" , nullable = false , length = 50)
    private String address;

    @Column(name = "major" , nullable = false , length = 50)
    private String major;

    @Column(name = "degree" , nullable = false , length = 50)
    private String degree;

    @Column(name = "study_program" , nullable = false , length = 50)
    private String studyProgram;

    @Column(name = "course" , nullable = false , length = 50)
    private String course;

    @Column(name = "status" , nullable = false , length = 50)
    private String status;


}
