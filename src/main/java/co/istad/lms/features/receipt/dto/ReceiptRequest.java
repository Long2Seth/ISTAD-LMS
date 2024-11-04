package co.istad.lms.features.receipt.dto;

import co.istad.lms.domain.Payment;
import co.istad.lms.features.payment.dto.PaymentReceipt;
import lombok.Builder;

import java.util.List;


@Builder
public record ReceiptRequest(
        String remarks,
        List<PaymentReceipt> payments

) {
}
