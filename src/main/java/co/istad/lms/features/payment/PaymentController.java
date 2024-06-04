package co.istad.lms.features.payment;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;


    @PreAuthorize("hasAnyAuthority('admin:control','adcademic:read')")
    @GetMapping
    public Page<PaymentResponse> getPayments(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return paymentService.getPayments(pageNumber, pageSize);

    }


    @PreAuthorize("hasAnyAuthority('admin:control','adcademic:read')")
    @GetMapping("/{uuid}")
    public PaymentResponse getPaymentById(@PathVariable String uuid) {
        return paymentService.getPaymentById(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:write')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public PaymentResponse updatePayment(@PathVariable String uuid,  @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(uuid, paymentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deletePayment(@PathVariable String uuid) {
        paymentService.deletePayment(uuid);
    }

    @GetMapping("/filter")
    public Page<HistoryPaymentResponse> filterPayments(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestBody BaseSpecification.FilterDto filterDto
    ) {
        return paymentService.filterPayment(filterDto, pageNumber, pageSize);
    }


}
