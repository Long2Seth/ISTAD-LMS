package co.istad.lms.features.payment;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.payment.dto.HistoryPaymentResponse;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.payment.dto.PaymentResponse;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage payments
 *
 * @since 1.0 (2024)
 * @author Long Piseth
 * @version 1.0

 */
public interface PaymentService {


    /**
     * Creates a new payment.
     *
     * @param paymentRequest is the request object containing payment details for create payment
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void createPayment(PaymentRequest paymentRequest);


    /**
     * Retrieves a paginated list of all payments.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<PaymentResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<PaymentResponse> getPayments(int page, int limit);

//    Page<PaymentResponse> getLatestPaymentsForAllStudents(int page, int limit);


    /**
     * Retrieves the details of a payment by its UUID.
     *
     * @param uuid is the unique identifier of payment
     * @return {@link PaymentResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    PaymentResponse getPaymentById(String uuid);



    /**
     * Updates an existing payment.
     *
     * @param uuid    is the unique identifier of payment
     * @param paymentRequest the request object containing the updated payment details
     * @return {@link PaymentResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    PaymentResponse updatePayment(String uuid, HistoryPaymentResponse paymentRequest);



    /**
     * Delete payment by  UUID that hard delete.
     *
     * @param uuid is the unique identifier of payment
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deletePayment(String uuid);



    /**
     * Retrieves a paginated list of all payments by student UUID.
     *
     * @param filterDto is the filter object containing payment details for filter payment
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<PaymentResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<HistoryPaymentResponse> filterPayment(BaseSpecification.FilterDto filterDto, int page, int limit );

}
