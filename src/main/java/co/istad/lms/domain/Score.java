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

    @Column(  nullable = false,unique = true )
    private String uuid;

    @Column(nullable = false)
    private Integer semester;

    private Double activityScore;

    private Double attendanceScore;

    private Double midtermExamScore;

    private Double finalExamScore;

    private Double miniProjectScore;

    private Double assignmentScore;

    private String studentAlias;

    private String courseAlias;

    @Column(nullable = false)
    private Boolean isDeleted = false;


}
