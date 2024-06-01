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
    @NotNull(message = "Paid amount is required")
    @Positive(message = "Paid amount must be positive")
    private Double paidAmount;

    @Column(nullable = false)
    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @Column(nullable = false)
    @NotNull(message = "Discount is required")
    @PositiveOrZero(message = "Discount cannot be negative")
    private Double discount;

    @Column(nullable = false)
    @NotNull(message = "Due amount is required")
    @PositiveOrZero(message = "Due amount cannot be negative")
    private Double dueAmount;

    @Column(nullable = false)
    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be positive")
    private Double totalAmount;

    @Column(nullable = false)
    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be after 2000")
    @Max(value = 2100, message = "Year must be before 2100")
    private Integer year;

    @Column(nullable = false)
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 2, message = "Semester can be at most 2")
    private Integer semester;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private Boolean status;

    @Column(nullable = false)
    @NotNull(message = "IsDeleted flag is required")
    private Boolean isDeleted;

    @ManyToOne
    @NotNull(message = "Student is required")
    private Student student;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Receipt> receipt;
}
