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
    public Page<ReceiptResponse> getReceipts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ){
        Page<ReceiptResponse> receipts = receiptService.getReceipts(page, limit);
        return receipts;
    }
    @PostMapping
    public ReceiptResponse createReceipt(@Valid @RequestBody ReceiptRequest receiptRequest){
        return  receiptService.createReceipt(receiptRequest);
    }

    @GetMapping("/{uuid}")
    public ReceiptResponse getReceipt(@PathVariable String uuid){
        return receiptService.getReceipt(uuid);
    }

    @PutMapping("/{uuid}")
    public ReceiptResponse updateReceipt(@PathVariable String uuid, @Valid @RequestBody ReceiptRequest receiptRequest){
        return receiptService.updateReceipt(uuid, receiptRequest);
    }

    @DeleteMapping("/{uuid}")
    public void deleteReceipt(@PathVariable String uuid){
        receiptService.deleteReceipt(uuid);
    }
}
