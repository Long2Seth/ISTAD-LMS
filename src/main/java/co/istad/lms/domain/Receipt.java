package co.istad.lms.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "receipts")
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "uuid" , nullable = false )
    private String uuid;

    @Column ( name = "paid_amount" , nullable = false )
    private Double paidAmount;

    @Column ( name = "payment_date" , nullable = false )
    private LocalDate paymentDate;

    @Column ( name = "discount" , nullable = false )
    private Double discount;

    @Column( name = "remarks" , columnDefinition = "TEXT")
    private String remarks;

    @Column( name = "create_by" )
    private String createBy;

    @Column ( name = "created_date" , nullable = false )
    private LocalDate createdDate;

    @Column ( name = "modified_by" )
    private String modifiedBy;

    @Column ( name = "modified_date" , nullable = false )
    private LocalDate modifiedDate;

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column ( name = "payment_id" , nullable = false)
    private Long paymentId;

}
