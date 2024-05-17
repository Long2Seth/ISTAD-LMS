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
public class Lecture {

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

    @Column( name = "created_by" , nullable = false , length = 50)
    private String createdBy;

    @Column( name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @Column( name = "modified_by" , nullable = false , length = 50)
    private String modifiedBy;

    @Column( name = "modified_at" , nullable = false)
    private LocalDate modifiedAt;

    @Column( name = "status" , nullable = false)
    private Boolean status;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column( name = "course_id" , nullable = false)
    private Long courseId;



}
