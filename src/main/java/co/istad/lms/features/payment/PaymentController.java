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
    public ResponseEntity<Page<PaymentResponse>> getPayments(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ) {
        Page<PaymentResponse> payments = paymentService.getPayments(page, limit);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable String uuid) {
        PaymentResponse paymentResponse = paymentService.getPaymentById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable String uuid, @Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.updatePayment(uuid, paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<PaymentResponse> deletePayment(@PathVariable String uuid) {
        PaymentResponse paymentResponse = paymentService.deletePayment(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(paymentResponse);
    }
}
