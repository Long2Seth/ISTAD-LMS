package co.istad.istadlms.domain.roles;


import co.istad.istadlms.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "academic")
@Entity
public class Academic {

    @Id
    private Long id;

    @OneToOne
    private User user;
}
