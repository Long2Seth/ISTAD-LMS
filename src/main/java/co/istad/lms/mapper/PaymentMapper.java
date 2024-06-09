package co.istad.lms.mapper;


import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toPaymentResponse(Payment payment);

    HistoryPaymentResponse toHistoryPaymentResponse(Payment payment);

    Payment toPaymentRequest(PaymentRequest paymentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentFromRequest(@MappingTarget Payment payment, HistoryPaymentResponse paymentRequest);


}
