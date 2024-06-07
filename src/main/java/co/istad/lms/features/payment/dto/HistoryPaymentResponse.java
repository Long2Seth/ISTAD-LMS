package co.istad.lms.features.payment.dto;

import java.time.LocalDate;

public record HistoryPaymentResponse (
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
