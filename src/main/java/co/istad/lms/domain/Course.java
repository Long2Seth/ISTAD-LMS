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

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private String subjectAlias;

    @Column(nullable = false)
    private String instructorAlias;

    @Column(nullable = false)
    private String classAlias;


}
