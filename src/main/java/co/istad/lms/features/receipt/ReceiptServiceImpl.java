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

    @Override
    public Page<ReceiptResponse> getReceipts(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Receipt> receipts = receiptRepository.findAll(pageRequest);
        return receipts.map(receiptMapper::toReceiptResponse);
    }

    @Override
    public ReceiptResponse createReceipt(ReceiptRequest receiptRequest) {
        Receipt receipt = receiptMapper.toReceipt(receiptRequest);
        receipt.setUuid(UUID.randomUUID().toString());
        receipt.setRemarks(receiptRequest.remarks());
        receipt.setIsDeleted(false);

        PaymentReceipt paymentReceipt = receiptRequest.payments().get(0);
        Payment payment = paymentRepository.findByUuid(paymentReceipt.uuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Payment with uuid = %s has not been found!", paymentReceipt.uuid())
                ));
        receipt.setPayment(payment);

        Receipt savedReceipt = receiptRepository.save(receipt);
        return receiptMapper.toReceiptResponse(savedReceipt);
    }

    @Override
    public ReceiptResponse getReceipt(String uuid) {
        Receipt receipt = receiptRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Receipt with uuid = %s has not been found!", uuid)));
        return receiptMapper.toReceiptResponse(receipt);
    }

    @Override
    public ReceiptResponse updateReceipt(String uuid, ReceiptRequest receiptRequest) {
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
        Receipt receipt = receiptRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Receipt with uuid = %s has not been found!", uuid))
                );
        receiptRepository.delete(receipt);
    }
}
