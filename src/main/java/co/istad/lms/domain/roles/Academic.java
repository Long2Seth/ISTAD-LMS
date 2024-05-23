package co.istad.lms.domain.roles;


import co.istad.lms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "academics")
@Entity
public class Academic {

    @Id
    private Long id;

    @Column(unique = true)
    String uuid;

    @OneToOne
    private User user;
}
