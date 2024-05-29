package co.istad.lms.domain;

import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String uuid;

    @Column(nullable = false)
    private Double paidAmount;

    @Column( nullable = false)
    private LocalDate paymentDate;

    @Column( nullable = false)
    private Double discount;

    @Column( nullable = false)
    private Double dueAmount;

    @Column(nullable = false)
    private Double totalAmount;

    @Column( nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;

    @Column( columnDefinition = "TEXT")
    private String remark;

    @Column( nullable = false)
    private Boolean status;

    @Column( nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List <Receipt> receipt;

}
