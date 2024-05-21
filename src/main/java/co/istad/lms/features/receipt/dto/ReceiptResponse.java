package co.istad.lms.features.receipt.dto;

import co.istad.lms.features.payment.dto.PaymentResponse;
import lombok.Builder;

@Builder
public record ReceiptResponse(

        String remarks,
        Boolean isDeleted,
        PaymentResponse payment
) {
}
