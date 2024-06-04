package co.istad.lms.features.payment;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import org.springframework.data.domain.Page;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);

    Page<PaymentResponse> getPayments(int page, int limit);

    PaymentResponse getPaymentById(String uuid);

    PaymentResponse updatePayment(String uuid, PaymentRequest paymentRequest);

    void deletePayment(String uuid);

    Page<HistoryPaymentResponse> filterPayment(BaseSpecification.FilterDto filterDto, int page, int size);

}
