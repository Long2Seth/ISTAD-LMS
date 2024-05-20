package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "subjects")
@Entity
public class Subject extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alias" , nullable = false )
    private String alias;

    @Column(name = "subject_name" , nullable = false )
    private String subjectName;

    @Column(name = "description" , nullable = false )
    private String description;

    @Column(name = "subject_logo" , nullable = false )
    private String subjectLogo;

    @Column(name = "credit" )
    private Integer credit;

    @Column( name = "duration" )
    private Integer duration;

}
