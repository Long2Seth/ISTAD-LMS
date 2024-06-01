package co.istad.lms.features.payment.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentRequest(

        @NotNull(message = "Paid amount is required")
        @Positive(message = "Paid amount must be positive")
        Double paidAmount,

        @NotNull(message = "Payment date is required")
        LocalDate paymentDate,

        @NotNull(message = "Discount is required")
        @PositiveOrZero(message = "Discount cannot be negative")
        Double discount,

        @NotNull(message = "Due amount is required")
        @PositiveOrZero(message = "Due amount cannot be negative")
        Double dueAmount,

        @NotNull(message = "Total amount is required")
        @Positive(message = "Total amount must be positive")
        Double totalAmount,

        @NotNull(message = "Year is required")
        Integer year,

        @NotNull(message = "Semester is required")
        @Min(value = 1, message = "Semester must be at least 1")
        @Max(value = 2, message = "Semester can be at most 2")
        Integer semester,

        String remark,

        String studentUuid
) {
}
