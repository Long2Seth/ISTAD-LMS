package co.istad.lms.domain.roles;


import co.istad.lms.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne
    private User user;
}
