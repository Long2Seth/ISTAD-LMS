package co.istad.lms.features.payment;


import co.istad.lms.domain.Payment;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.mapper.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(@Valid PaymentRequest paymentRequest) {

        //
//        Student student = studentRepository.findByUuid(paymentRequest.)
//                .orElseThrow(
//                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                String.format("Student with uuid = %s have been not found", paymentRequest.studentUuid()))
//                );

        Payment payment = paymentMapper.toPayment(paymentRequest);
        payment.setUuid(UUID.randomUUID().toString());
        payment.setStatus(false);
        payment.setIsDeleted(false);
        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public Page<PaymentResponse> getPayments(int page, int limit) {
        // get all payments sorted by id in descending order
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Payment> payments = paymentRepository.findAll(pageRequest);
        // return payments that found
        return payments.map(paymentMapper::toPaymentResponse);

    }

    @Override
    public PaymentResponse getPaymentById(String uuid) {

        // find payment by uuid
        Payment payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String .format("Payment with uuid = %s have been not found", uuid)));
        // return payment that found
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse updatePayment(String uuid, PaymentRequest paymentRequest) {

        // find payment by uuid
        Payment payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Payment with uuid = %s have been not found", uuid))
                );

        // update payment
        payment.setPaidAmount(paymentRequest.paidAmount());
        payment.setPaymentDate(paymentRequest.paymentDate());
        payment.setDiscount(paymentRequest.discount());
        payment.setDueAmount(paymentRequest.dueAmount());
        payment.setTotalAmount(paymentRequest.totalAmount());
        payment.setYear(paymentRequest.year());
        payment.setSemester(paymentRequest.semester());
        payment.setRemark(paymentRequest.remark());

        // save payment
        paymentRepository.save(payment);

        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public void deletePayment(String uuid) {
        Payment payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Payment with uuid = %s have been not found", uuid))
                );
        paymentRepository.delete(payment);
        
        paymentMapper.toPaymentResponse(payment);

    }
}
