package co.istad.lms.mapper;


import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , uses = { StudentMapper.class , UserMapper.class})
public interface PaymentMapper {



    @Mapping(target = "studentProfile", source = "payment.student.user.profileImage")
    @Mapping(target = "gender", source = "payment.student.user.gender")
    PaymentResponse toPaymentResponse(Payment payment);

    HistoryPaymentResponse toHistoryPaymentResponse(Payment payment);

    @Mapping(target = "payment.paidDate" , ignore = true)
    Payment toPaymentRequest(PaymentRequest paymentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentFromRequest(@MappingTarget Payment payment, HistoryPaymentResponse paymentRequest);


}
