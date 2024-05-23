package co.istad.lms.features.receipt;

import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import org.springframework.data.domain.Page;

public interface ReceiptService {

    Page<ReceiptResponse> getReceipts(int page, int limit);

    ReceiptResponse createReceipt(ReceiptRequest receiptRequest);

    ReceiptResponse getReceipt(String uuid);

    ReceiptResponse updateReceipt(String uuid ,ReceiptRequest receiptRequest);

    void deleteReceipt(String uuid);

}
