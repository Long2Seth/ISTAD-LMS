package co.istad.lms.features.receipt;

import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;

public interface ReceiptService {

    ReceiptResponse createReceipt(ReceiptRequest receiptRequest);

    ReceiptResponse getReceipt(Long id);

    ReceiptResponse updateReceipt(Long id ,ReceiptRequest receiptRequest);

    void deleteReceipt(Long id);

}
