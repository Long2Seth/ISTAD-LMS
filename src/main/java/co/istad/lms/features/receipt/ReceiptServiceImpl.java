package co.istad.lms.features.receipt;

import co.istad.lms.domain.Payment;
import co.istad.lms.domain.Receipt;
import co.istad.lms.features.payment.PaymentRepository;
import co.istad.lms.features.payment.dto.PaymentReceipt;
import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import co.istad.lms.mapper.ReceiptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;
    private final ReceiptMapper receiptMapper;


    // Private method to get the payment
    @Override
    public Page<ReceiptResponse> getReceipts(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Receipt> receipts = receiptRepository.findAll(pageRequest);
        return receipts.map(receiptMapper::toReceiptResponse);
    }

    @Override
    public void createReceipt(ReceiptRequest receiptRequest) {
        // Create a new receipt
        Receipt receipt = receiptMapper.toReceipt(receiptRequest);

        // Set some fields of the receipt
        receipt.setUuid(UUID.randomUUID().toString());
        receipt.setRemarks(receiptRequest.remarks());
        receipt.setIsDeleted(false);

        PaymentReceipt paymentReceipt = receiptRequest.payments().get(0);
        Payment payment = paymentRepository.findByUuid(paymentReceipt.uuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Payment with uuid = %s has not been found!", paymentReceipt.uuid())
                ));

        // Set the payment tu the receipt
        receipt.setPayment(payment);

        //Save the receipt
       receiptRepository.save(receipt);
    }

    @Override
    public ReceiptResponse getReceipt(String uuid) {

        // Fine the receipt by uuid
        Receipt receipt = receiptRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Receipt with uuid = %s has not been found!", uuid)));

        return receiptMapper.toReceiptResponse(receipt);

    }

    @Override
    public ReceiptResponse updateReceipt(String uuid, ReceiptRequest receiptRequest) {

        // Find the receipt by uuid that is passed
        Receipt receipt = receiptRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Receipt with uuid = %s has not been found!", uuid)));

        receipt.setRemarks(receiptRequest.remarks());
        receipt.setIsDeleted(false);

        PaymentReceipt paymentReceipt = receiptRequest.payments().get(0);
        Payment payment = paymentRepository.findByUuid(paymentReceipt.uuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Payment with uuid = %s has not been found!", paymentReceipt.uuid())));
        receipt.setPayment(payment);

        return receiptMapper.toReceiptResponse(receiptRepository.save(receipt));
    }

    @Override
    public void deleteReceipt(String uuid) {

        // Find the receipt by uuid that is passed
        Receipt receipt = receiptRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Receipt with uuid = %s has not been found!", uuid))
                );

        // Delete the receipt by calling the delete method of the receiptRepository
        receiptRepository.delete(receipt);
    }
}
