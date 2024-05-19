package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "lectures")
@Entity
public class Lecture extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false)
    private String alias;

    @Column( name = "strat_time" , nullable = false)
    private String startTime;

    @Column( name = "end_time" , nullable = false)
    private String endTime;

    @Column( name = "description" , nullable = false)
    private String description;

    @Column( name = "lecture_date" , nullable = false)
    private LocalDate lectureDate;

    @Column( name = "status" , nullable = false)
    private Boolean status;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column( name = "course_id" , nullable = false)
    private Long courseId;



}
