package co.istad.lms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "file_meta_data")
@Entity
public class FileMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique = true,length = 100)
    String fileName;

    String contentType;

    String folder;

    Long fileSize;

    String extension;

}
