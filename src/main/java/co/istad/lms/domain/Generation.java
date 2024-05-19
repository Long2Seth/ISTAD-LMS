package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "generations")
@Entity
public class Generation extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50 , name = "alias")
    private String alias;

    @Column(nullable = false , length = 50 , name = "name")
    private String name;

    @Column(nullable = false , length = 50 , name = "description")
    private String description;

    @Column( name = "start_year" , nullable = false)
    private Integer startYear;

    @Column( name = "end_year" , nullable = false)
    private Integer endYear;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;


}
