package co.istad.lms.features.payment.dto;

public record HistoryPaymentResponse (
        String uuid ,
        String studentName ,
        String gender,
        String paymentDate,
        double discount,
        double totalPayment,
        double balanceDue,
        double courseFee,
        String paymentMethod,
        boolean status ,
        String remarks
) {
}
