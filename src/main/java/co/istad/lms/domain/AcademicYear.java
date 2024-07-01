package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
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
public class AcademicYear extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false,length = 100,unique = true)
    private String alias;

    @Column(nullable = false , length = 50)
    private String academicYear;

    @Column(nullable = false)
    Integer status;

    @Column(nullable = false)
    Boolean isDraft;

    @Column(nullable = false)
    Boolean isDeleted;


}
