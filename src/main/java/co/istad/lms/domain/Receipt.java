package co.istad.lms.domain;


import co.istad.lms.config.jpa.Auditable;
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
public class Receipt extends Auditable {

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

    @Column( name = "is_deleted" , nullable = false)
    private Boolean isDeleted;

    @Column ( name = "payment_id" , nullable = false)
    private Long paymentId;

}
