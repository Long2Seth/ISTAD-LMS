package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "shifts")
@Entity
public class Shift extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)

    private LocalDateTime startTime;

    @Column(nullable = false)

    private LocalDateTime endTime;

    @Column(nullable = false)
    private String weekday;

    private Boolean description;

    @Column(nullable = false)
    private Boolean isDeleted;

}
