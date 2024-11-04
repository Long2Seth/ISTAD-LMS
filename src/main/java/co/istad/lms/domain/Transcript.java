package co.istad.lms.domain;

import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.StudentTranscriptResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "transcripts")
@Entity
public class Transcript extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length =100)
    private String uuid;

    @Column(nullable = false)
    private String classCode;

    private Double semester1;

    private Double semester2;

    private Double jpa;

    private Double total;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;


}
