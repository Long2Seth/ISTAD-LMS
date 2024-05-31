package co.istad.lms.features.receipt;

import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;



    @PreAuthorize("hasAnyAuthority('admin:control','adcademic:read')")
    @GetMapping
    public Page<ReceiptResponse> getReceipts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ){
        Page<ReceiptResponse> receipts = receiptService.getReceipts(page, limit);
        return receipts;
    }


    @PreAuthorize("hasAnyAuthority('admin:control','adcademic:write')")
    @PostMapping
    public ReceiptResponse createReceipt(@Valid @RequestBody ReceiptRequest receiptRequest){
        return  receiptService.createReceipt(receiptRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping("/{uuid}")
    public ReceiptResponse getReceipt(@PathVariable String uuid){
        return receiptService.getReceipt(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public ReceiptResponse updateReceipt(@PathVariable String uuid, @Valid @RequestBody ReceiptRequest receiptRequest){
        return receiptService.updateReceipt(uuid, receiptRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:delete')")
    @DeleteMapping("/{uuid}")
    public void deleteReceipt(@PathVariable String uuid){
        receiptService.deleteReceipt(uuid);
    }
}
