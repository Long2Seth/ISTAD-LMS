package co.istad.lms.features.receipt;


import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
@Slf4j
public class ReceiptController {


    private final ReceiptService receiptService;


    @PostMapping
    public ReceiptResponse createReceipt(@RequestBody ReceiptRequest receiptRequest){
        return receiptService.createReceipt(receiptRequest);
    }

    @GetMapping("/{id}")
    public ReceiptResponse getReceipt(@PathVariable Long id){
        return receiptService.getReceipt(id);
    }

    @PutMapping("/{id}")
    public ReceiptResponse updateReceipt(@PathVariable Long id, @RequestBody ReceiptRequest receiptRequest){
        return receiptService.updateReceipt(id, receiptRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id){
        receiptService.deleteReceipt(id);
    }


}
