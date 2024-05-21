package co.istad.lms.features.payment;


import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public Page<PaymentResponse> getPayments(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        return paymentService.getPayments(page, limit);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping("/{id}")
    public PaymentResponse updatePayment(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(id, paymentRequest);
    }

    @DeleteMapping("/{id}")
    public PaymentResponse deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }


}
