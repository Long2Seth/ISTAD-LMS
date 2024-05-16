package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "attendances")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "uuid" , nullable = false )
    private String uuid;

    @Column ( name = "status" , nullable = false )
    private Boolean status;

    @Column ( name = "note")
    private String note;

    @Column ( name = "student_id" , nullable = false )
    private Long studentId;

    @Column (name = "lecture_id" , nullable = false)
    private Long lectureId;
}
