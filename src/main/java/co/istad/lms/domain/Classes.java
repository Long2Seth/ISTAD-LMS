package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "classes")
@Entity
public class Classes extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false)
    private String alias;

    @Column( name = "class_name" , nullable = false)
    private String className;

    @Column( name = "description" , nullable = false)
    private String description;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;


}
