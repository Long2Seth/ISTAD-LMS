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
@Table(name = "admissions")
@Entity
public class Admission extends Auditable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private Integer status;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false)
    private LocalDate openDate;
    
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isDeleted = false;


}
