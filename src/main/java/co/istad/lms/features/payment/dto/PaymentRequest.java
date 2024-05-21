package co.istad.lms.features.payment.dto;


import jakarta.persistence.Column;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentRequest(
         Double paidAmount,
         LocalDate paymentDate,
         Double discount,
         Double dueAmount,
         Double totalAmount,
         Integer year,
         Integer semester,
         String remark,
         Boolean status,
         Boolean isDeleted
) {
}
