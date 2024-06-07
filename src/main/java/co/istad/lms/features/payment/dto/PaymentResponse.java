package co.istad.lms.features.payment.dto;

import jakarta.persistence.Column;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentResponse(

        String uuid,
        String studentName,
        String studentProfile,
        String gender,
        Boolean status,
        Double balanceDue,
        Double paidAmount,
        LocalDate paidDate,
        Double discount,
        Double originalPayment,
        Double totalPayment,
        Double courseFee,
        String paymentMethod,
        String remark


) {
}
