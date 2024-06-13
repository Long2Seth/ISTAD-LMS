package co.istad.lms.domain.roles;

import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "staffs")
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(columnDefinition = "TEXT")
    private String position;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
