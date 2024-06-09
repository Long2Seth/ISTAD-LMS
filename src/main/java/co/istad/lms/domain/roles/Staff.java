package co.istad.lms.domain.roles;



import co.istad.lms.config.jpa.Auditable;
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
public class Staff extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String uuid;

    String position;

    boolean status;

    boolean isDeleted;

    @OneToOne
    private User user;
}
