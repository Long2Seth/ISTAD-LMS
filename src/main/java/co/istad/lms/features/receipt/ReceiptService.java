package co.istad.lms.features.receipt;

import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage receipts
 *
 * @since 1.0 (2024)
 * @author Long Piseth
 *
 */
public interface ReceiptService {


    /**
     * Retrieves a paginated list of all receipts.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<ReceiptResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    Page<ReceiptResponse> getReceipts(int page, int limit);


    /**
     * Creates a new receipt.
     *
     * @param receiptRequest is the request object containing receipt details for create receipt
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void createReceipt(ReceiptRequest receiptRequest);


    /**
     * Retrieves the details of a receipt by its UUID.
     *
     * @param uuid is the unique identifier of receipt
     * @return {@link ReceiptResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    ReceiptResponse getReceipt(String uuid);


    /**
     * Updates an existing receipt.
     *
     * @param uuid    is the unique identifier of receipt
     * @param receiptRequest the request object containing the updated receipt details
     * @return {@link ReceiptResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    ReceiptResponse updateReceipt(String uuid ,ReceiptRequest receiptRequest);


    /**
     * Delete receipt by  UUID.
     *
     * @param uuid is the unique identifier of receipt
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void deleteReceipt(String uuid);

}
