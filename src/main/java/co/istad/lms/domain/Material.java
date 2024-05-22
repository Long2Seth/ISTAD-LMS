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

    @Column( nullable = false )
    private String alias;

    @Column ( nullable = false )
    private String title;

    private String contentType;

    private String extension;

    private Long size;

    @Column( nullable = false )
    private String fileUrl;

    private String thumbnail;

    private String description;

    @Column (nullable = false)
    private String subjectAlias;

}
