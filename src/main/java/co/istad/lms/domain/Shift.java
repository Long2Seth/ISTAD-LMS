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

    @Column(name = "alias", nullable = false, unique = true)
    private String alias;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)

    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)

    private LocalDateTime endTime;

    @Column(name = "weekday", nullable = false)
    private String weekday;

    @Column(name = "description")
    private Boolean description;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}
