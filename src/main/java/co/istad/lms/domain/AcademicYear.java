package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "academic_years")
@Entity
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false,length = 100)
    private String uuid;

    @Column(nullable = false , length = 50)
    private String year;

    @Column(nullable = false)
    private LocalDate startYear;

    @Column(nullable = false)
    private LocalDate endYear;

}
