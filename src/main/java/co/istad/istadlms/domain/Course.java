package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false)
    private String alias;

    @Column( name = "status" , nullable = false)
    private Integer status;

    @Column( name = "created_by" , length = 50)
    private String createdBy;

    @Column( name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @Column( name = "modified_by" , length = 50)
    private String modifiedBy;

    @Column( name = "modified_at" , nullable = false)
    private LocalDate modifiedAt;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column( name = "subject_id" , nullable = false)
    private Long subjectId;

    @Column( name = "instructor_id" , nullable = false)
    private Long instructorId;

    @Column( name = "class_id" , nullable = false)
    private Long classId;


}
