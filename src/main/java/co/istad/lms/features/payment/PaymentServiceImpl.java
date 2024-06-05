package co.istad.lms.features.payment;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final BaseSpecification<Payment> baseSpecification;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public PaymentResponse createPayment(@Valid PaymentRequest paymentRequest) {

        User user = userRepository.findByUsername(paymentRequest.studentName())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with username = %s have been not found", paymentRequest.studentName())
                        )
                );

        // Retrieve existing payments for the student
        List<Payment> existingPayments = paymentRepository.findByStudentName(paymentRequest.studentName());

            // Create a new payment if no existing payments are found
            Payment payment = new Payment();

            payment.setUuid(UUID.randomUUID().toString());
            payment.setStudentName(paymentRequest.studentName());
            payment.setStudentProfile(user.getProfileImage());
            payment.setGender(user.getGender());

            payment.setOriginalPayment(paymentRequest.originalPayment());
            payment.setDiscount(paymentRequest.discount());
            payment.setPaidAmount(paymentRequest.paidAmount());
            payment.setPaidDate(paymentRequest.paidDate());
            payment.setPaymentMethod(paymentRequest.paymentMethod());
            payment.setRemark(paymentRequest.remarks());

            // Initial calculation
            payment.setTotalPayment(paymentRequest.paidAmount());
            payment.setCourseFee(paymentRequest.originalPayment() - (paymentRequest.originalPayment() * (paymentRequest.discount() / 100)));
            payment.setBalanceDue(payment.getCourseFee() - payment.getTotalPayment());
            payment.setStatus(Boolean.valueOf(payment.getBalanceDue() == 0 ? "Paired" : "Unpaid"));

        // Save the payment to the repository
        paymentRepository.save(payment);

        // Return the response
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
                                String.format("Payment with uuid = %s have been not found", uuid)));
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
//        payment.setPaidAmount(paymentRequest.paidAmount());
//        payment.setPaymentDate(paymentRequest.paymentDate());
//        payment.setDiscount(paymentRequest.discount());
//        payment.setDueAmount(paymentRequest.dueAmount());
//        payment.setTotalAmount(paymentRequest.totalAmount());
//        payment.setYear(paymentRequest.year());
//        payment.setSemester(paymentRequest.semester());
//        payment.setRemark(paymentRequest.remark());

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


    @Override
    public Page<HistoryPaymentResponse> filterPayment(BaseSpecification.FilterDto filterDto, int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        Specification<Payment> specification = baseSpecification.filter(filterDto);

        Page<Payment> payments = paymentRepository.findAll(specification, pageRequest);

        return payments.map(paymentMapper::toHistoryPaymentResponse);

    }
}
