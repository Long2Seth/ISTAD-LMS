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
import co.istad.lms.util.DateTimeUtil;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final BaseSpecification<Payment> baseSpecification;
    private final UserRepository userRepository;

    @Override
    public void createPayment(@Valid PaymentRequest paymentRequest) {


        


        // Create a new payment
        Payment payment = paymentMapper.toPaymentRequest(paymentRequest);
        // Convert paidDate from string to LocalDate
        LocalDate paidDate = DateTimeUtil.stringToLocalDate(paymentRequest.paidDate(),"paidDate");

        payment.setUuid(UUID.randomUUID().toString());
        payment.setPaidDate(paidDate);

        // Do logic discount
        Double paidDiscount = payment.getAcademicFee() * payment.getDiscount() / 100;

        // Set academic fee after discount
        payment.setAcademicFee(payment.getAcademicFee() - paidDiscount);

        // Set total payment
        payment.setTotalPayment(paymentRequest.paidAmount());

        // Save the payment to the repository
        paymentRepository.save(payment);


    }


    @Override
    public Page<PaymentResponse> getPayments(int page, int limit) {
        // get all payments sorted by id in descending order
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
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
    public PaymentResponse updatePayment(String uuid, HistoryPaymentResponse paymentRequest) {

        // find payment by uuid
        Payment payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Payment with uuid = %s have been not found", uuid))
                );

        // update payment
        paymentMapper.updatePaymentFromRequest(payment, paymentRequest);

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
