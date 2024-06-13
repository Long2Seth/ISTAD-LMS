package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "scores")
@Entity
public class Score extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(  nullable = false,unique = true,length = 100)
    private String uuid;
    

    private Double activityScore;

    private Double attendanceScore;

    private Double midtermExamScore;

    private Double finalExamScore;

    private Double miniProjectScore;

    private Double assignmentScore;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @Column(nullable = false)
    private Boolean isDeleted ;


}
