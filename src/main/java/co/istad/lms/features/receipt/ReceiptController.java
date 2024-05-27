package co.istad.lms.features.receipt;

import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;


    @GetMapping
    public ResponseEntity<Page<ReceiptResponse>> getReceipts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ){
        Page<ReceiptResponse> receipts = receiptService.getReceipts(page, limit);
        return ResponseEntity.ok().body(receipts);
    }
    @PostMapping
    public ResponseEntity<ReceiptResponse> createReceipt(@Valid @RequestBody ReceiptRequest receiptRequest){
        ReceiptResponse receiptResponse = receiptService.createReceipt(receiptRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(receiptResponse);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable String uuid){
        ReceiptResponse receiptResponse = receiptService.getReceipt(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(receiptResponse);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ReceiptResponse> updateReceipt(@PathVariable String uuid, @Valid @RequestBody ReceiptRequest receiptRequest){
        ReceiptResponse receiptResponse = receiptService.updateReceipt(uuid, receiptRequest);
        return ResponseEntity.status(HttpStatus.OK).body(receiptResponse);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable String uuid){
        receiptService.deleteReceipt(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
