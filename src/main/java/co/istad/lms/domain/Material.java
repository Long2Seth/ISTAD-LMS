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

    @Column( name = "alias" , nullable = false )
    private String alias;

    @Column ( name = "title" , nullable = false )
    private String title;

    @Column( name = "content_type" )
    private String contentType;

    @Column( name = "extension")
    private String extension;

    @Column( name = "size" )
    private Long size;

    @Column( name = "file_url" , nullable = false )
    private String fileUrl;

    @Column ( name = "thumbnail" )
    private String thumbnail;

    @Column( name = "description" )
    private String description;

    @Column ( name = "subject_id" , nullable = false)
    private Long subjectId;

}
