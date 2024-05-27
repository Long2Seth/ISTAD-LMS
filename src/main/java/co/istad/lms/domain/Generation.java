package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "generations")
@Entity
public class Generation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String alias;

    @Column(nullable = false , length = 50)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer startYear;

    @Column(nullable = false)
    private Integer endYear;

    @Column(nullable = false)
    private Boolean isDeleted;


}
