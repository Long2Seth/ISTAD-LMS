package co.istad.lms.features.payment;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Payment;
import co.istad.lms.domain.User;
import co.istad.lms.domain.YearOfStudy;
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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;

    private final StudentRepository studentRepository;

    private final PaymentMapper paymentMapper;

    private final BaseSpecification<Payment> baseSpecification;

    private final UserRepository userRepository;


    @Override
    public void createPayment(@Valid PaymentRequest paymentRequest) {

        // Find student by username
        User user = userRepository.findByUsername(paymentRequest.userName())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Student with username = %s have been not found", paymentRequest.userName()))
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with username = %s have been not found", paymentRequest.userName()
                                )
                        )
                );

        // Get the courses of the student
        Set<Course> courses = student.getCourses();

        // Check if there are any courses
        if (courses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Student with username = %s is not enrolled in any courses", paymentRequest.userName()));
        }

        int paymentYear = paymentRequest.year();
        boolean yearMatch = false;
        // Check year with student's year of study
        for (Course course : courses) {
            YearOfStudy yearOfStudy = course.getYearOfStudy();
            if (yearOfStudy.getYear() == paymentYear) {
                yearMatch = true;
                break;
            }
        }

        if (!yearMatch) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The year of payment must match the student's current year of study");
        }


        // Create a new payment
        Payment payment = paymentMapper.toPaymentRequest(paymentRequest);

        payment.setStudentName(student.getUser().getNameEn());
        payment.setStudent(student); // Set the student to the payment

        // Convert paidDate from string to LocalDate
        LocalDate paidDate = DateTimeUtil.stringToLocalDate(paymentRequest.paidDate(), "paidDate");

        payment.setUuid(UUID.randomUUID().toString());
        payment.setPaidDate(paidDate);

        // Calculate the discount
        Double paidDiscount = payment.getAcademicFee() * payment.getDiscount() / 100;

        // Set academic fee after discount
        payment.setAcademicFee(payment.getAcademicFee() - paidDiscount);

        // Set total payment
        payment.setTotalPayment(paymentRequest.paidAmount());

        // Set balance due
        payment.setBalanceDue(payment.getAcademicFee() - payment.getTotalPayment());

        // Set paid complete status
        if (Objects.equals(payment.getTotalPayment(), payment.getAcademicFee())) {
            payment.setPaidComplete(true);
        } else {
            payment.setPaidComplete(false);
        }
        payment.setStatus(false);

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
