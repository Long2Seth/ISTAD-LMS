package co.istad.lms.features.payment.dto;


import lombok.Builder;

@Builder
public record PaymentReceipt
        (
                String uuid
        ){
}
