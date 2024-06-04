package co.istad.lms.mapper;


import co.istad.lms.domain.Payment;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toPaymentResponse(Payment payment);

    HistoryPaymentResponse toHistoryPaymentResponse(Payment payment);

    Payment toPayment(PaymentRequest paymentRequest);
}
