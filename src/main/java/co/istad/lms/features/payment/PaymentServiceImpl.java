package co.istad.lms.features.payment;


import co.istad.lms.domain.Payment;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import co.istad.lms.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{


    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public Page<PaymentResponse> getPayments(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Payment> payments = paymentRepository.findAll(pageRequest);
        return payments.map(paymentMapper::toPaymentResponse) ;

    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        return paymentMapper.toPaymentResponse(payment);

    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        payment.setPaidAmount(paymentRequest.paidAmount());
        payment.setPaymentDate(paymentRequest.paymentDate());
        payment.setDiscount(paymentRequest.discount());
        payment.setDueAmount(paymentRequest.dueAmount());
        payment.setTotalAmount(paymentRequest.totalAmount());
        payment.setYear(paymentRequest.year());
        payment.setSemester(paymentRequest.semester());
        payment.setRemark(paymentRequest.remark());
        payment.setStatus(paymentRequest.status());
        payment.setIsDeleted(paymentRequest.isDeleted());
        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        payment.setIsDeleted(true);
        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);

    }
}
