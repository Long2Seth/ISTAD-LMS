package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "payments")
@Entity
public class Payment extends Auditable{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "uuid" , nullable = false)
    private String uuid;

    @Column ( name = "paid_amount" , nullable = false)
    private Double paidAmount;

    @Column ( name = "payment_date" , nullable = false)
    private LocalDate paymentDate;

    @Column( name = "discount" , nullable = false)
    private Double discount;

    @Column ( name = "due_amount" , nullable = false)
    private Double dueAmount;

    @Column ( name = "total_amount" , nullable = false)
    private Double totalAmount;

    @Column ( name = "year" , nullable = false)
    private Integer year;

    @Column ( name = "semester" , nullable = false)
    private Integer semester;

    @Column  ( name = "remark" , columnDefinition = "TEXT")
    private String remark;

    @Column ( name = "student_id" , nullable = false)
    private Long studentId;

    @Column( name = "status" , nullable = false)
    private Boolean status;

    @Column ( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;




}

