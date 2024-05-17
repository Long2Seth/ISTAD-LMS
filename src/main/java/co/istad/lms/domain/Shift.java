package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "shifts")
@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false )
    private String alias;

    @Column ( name = "name" , nullable = false )
    private String name;

    @Column ( name = "start_time",nullable = false)
    private String startTime;

    @Column ( name = "end_time" , nullable = false)
    private String endTime;

    @Column( name ="weekday" , nullable = false)
    private String weekday;

    @Column ( name= "description")
    private String description;

    @Column ( name = "create_at", nullable = false)
    private Boolean createAt;

    @Column ( name = "created_by" )
    private String createdBy;

    @Column ( name = "modified_at" , nullable = false)
    private Boolean modifiedAt;

    @Column ( name = "modified_by" )
    private String modifiedBy;

    @Column ( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

}