package co.istad.lms.domain;

import co.istad.lms.config.jpa.Auditable;
import co.istad.lms.domain.roles.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(nullable = false, unique = true)
    @NotBlank(message = "UUID is required")
    private String uuid;


    @Column(nullable = false)
    private String studentName;


    private String studentProfile;


    @Column(nullable = false)
    private String gender;


    @Column(nullable = false)
    private Double balanceDue;


    @Column(nullable = false)
    private Double paidAmount;


    @Column(nullable = false)
    private LocalDate paidDate;


    @Column(nullable = false)
    private Double discount;


    @Column(nullable = false)
    private Double originalPayment;


    @Column(nullable = false)
    private Double totalPayment;


    private Double courseFee;


    private String paymentMethod;


    @Column(columnDefinition = "TEXT")
    private String remark;


    @Column(nullable = false)
    private Boolean status;


    @ManyToOne
    private Student student;


    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Receipt> receipt;
}
