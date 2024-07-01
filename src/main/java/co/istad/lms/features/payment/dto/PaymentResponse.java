package co.istad.lms.features.payment.dto;

import jakarta.persistence.Column;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentResponse(

        String uuid,


        String studentName,


        String studentProfile,


        String gender,


        Boolean status,


        Double balanceDue,


        Double paidAmount,


        LocalDate paidDate,


        Double discount,


        Double totalPayment,


        Double academicFee,


        String paymentMethod,


        // information about filter payment
        //generation , degree , faculty , academicYear , year , semester , className , shift
        String generation,


        String degree,


        String faculty,


        String academicYear,


        String year,


        String semester,


        String className,


        String shift,


        // remark about payment information
        String remark


) {
}
