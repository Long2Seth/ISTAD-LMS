package co.istad.lms.features.payment;

import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public Page<PaymentResponse> getPayments(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ) {
        Page<PaymentResponse> payments = paymentService.getPayments(page, limit);
        return payments;

    }

    @GetMapping("/{uuid}")
    public PaymentResponse getPaymentById(@PathVariable String uuid) {
        return paymentService.getPaymentById(uuid);
    }

    @PostMapping
    public PaymentResponse createPayment( @RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping("/{uuid}")
    public PaymentResponse updatePayment(@PathVariable String uuid, @Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(uuid, paymentRequest);
    }

    @DeleteMapping("/{uuid}")
    public PaymentResponse deletePayment(@PathVariable String uuid) {
        return paymentService.deletePayment(uuid);
    }
}
