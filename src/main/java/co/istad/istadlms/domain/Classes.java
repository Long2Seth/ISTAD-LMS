package co.istad.istadlms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "classes")
@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "alias" , nullable = false)
    private String alias;

    @Column( name = "class_name" , nullable = false)
    private String className;

    @Column( name = "description" , nullable = false)
    private String description;

    @Column( name = "created_by" , nullable = false , length = 50)
    private String createdBy;

    @Column( name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @Column( name = "modified_by" , nullable = false , length = 50)
    private String modifiedBy;

    @Column( name = "modified_at" , nullable = false)
    private LocalDate modifiedAt;


    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;


}
