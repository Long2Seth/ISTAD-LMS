package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
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

    @Column( name = "uuid" , nullable = false )
    private String UUID;

    @Column(name = "semester" , nullable = false)
    private Integer semester;

    @Column( name = "activity_score"  )
    private Double activityScore;

    @Column( name = "attendance_score"  )
    private Double attendanceScore;

    @Column( name ="midterm_exam_score"  )
    private Double midtermExamScore;

    @Column( name = "final_exam_score"  )
    private Double finalExamScore;

    @Column( name = "mini_project_score"  )
    private Double miniProjectScore;

    @Column( name ="assig nment_score"  )
    private Double assignmentScore;

    @Column( name = "student_id" , nullable = false )
    private Long studentId;

    @Column( name = "course_id" , nullable = false )
    private Long courseId;


}
