package co.istad.lms.features.payment.dto;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.StudentRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentRequest(



        @NotNull(message = "Student name be required")
        String  userName,



        @NotNull(message = "Payment fee be required")
        @Positive(message = "Payment fee must be positive")
        double academicFee,


        @NotNull(message = "Year be required")
        @Positive(message = "Year must be positive")
        Integer year,



        @NotNull(message = "Discount  be required")
        @Positive(message = "Discount must be positive")
        double discount,


        @NotNull(message = "Paid amount be required")
        @Positive(message = "Paid amount must be positive")
        Double paidAmount,


        @NotNull(message = "Due date  be required")
        String paidDate,




        @NotNull(message = "Payment method be required")
        String paymentMethod,


        @Size(max = 255 , message = "Remarks must be less than 255 characters")
        String remark
) {
}
