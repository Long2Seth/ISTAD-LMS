package co.istad.lms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "degrees")
@Entity
public class Degree extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50 , name = "alias")
    private String alias;

    @Column(nullable = false , length = 50 , name = "level")
    private String level;

    @Column(nullable = false , name = "description")
    private String description;

    private String modifiedBy;

    @Column(name = "is_deleted" , nullable = false)
    private Boolean is_deleted;


}
