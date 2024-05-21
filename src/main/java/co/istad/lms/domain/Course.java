package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
@Entity
public class Course extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false)
    private String alias;

    @Column( name = "status" , nullable = false)
    private Integer status;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column( name = "subject_id" , nullable = false)
    private Long subjectId;

    @Column( name = "instructor_id" , nullable = false)
    private Long instructorId;

    @Column( name = "class_id" , nullable = false)
    private Long classId;


}
