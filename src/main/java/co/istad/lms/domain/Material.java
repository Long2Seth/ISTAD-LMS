package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "materials")
@Entity
public class Material extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false ,unique = true)
    private String alias;

    @Column ( nullable = false )
    private String title;

    private String contentType;

    private String extension;

    private Long size;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String fileUrl;

    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column (nullable = false)
    private String subjectAlias;

}
