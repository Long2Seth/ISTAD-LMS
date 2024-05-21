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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public ReceiptResponse createReceipt(ReceiptRequest receiptRequest) {

        Receipt receipt = receiptMapper.toReceipt(receiptRequest);
        receipt.setUuid(UUID.randomUUID().toString());
        receipt.setRemarks(receiptRequest.remarks());
        receipt.setIsDeleted(false);
        System.out.println(receiptRequest.remarks());

        for (PaymentReceipt paymentReceipt : receiptRequest.payments()) {
            Payment payment = paymentRepository.findById(paymentReceipt.id())
                    .orElseThrow(() -> {
                        return new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Payment has not been found!");
                    });
            receipt.setPayment(payment);
        }
        Receipt savedReceipt = receiptRepository.save(receipt);

        return receiptMapper.toReceiptResponse(savedReceipt);
    }



    @Override
    public ReceiptResponse getReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Receipt has not been found!");
                });
        return receiptMapper.toReceiptResponse(receipt);

    }

    @Override
    public ReceiptResponse updateReceipt(Long id , ReceiptRequest receiptRequest) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Receipt has not been found!");
                });
        receipt.setRemarks(receiptRequest.remarks());
        receipt.setIsDeleted(false);
        List<Payment> payments = new ArrayList<>();
        for (PaymentReceipt paymentReceipt : receiptRequest.payments()) {
            Payment payment = paymentRepository.findById(paymentReceipt.id())
                    .orElseThrow(() -> {
                        return new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Payment has not been found!");
                    });
            payments.add(payment);
        }
        return receiptMapper.toReceiptResponse(receiptRepository.save(receipt));
    }

    @Override
    public void deleteReceipt(Long id) {

        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Receipt has not been found!");
                });
        receipt.setIsDeleted(true);
        receiptRepository.save(receipt);


    }
}
