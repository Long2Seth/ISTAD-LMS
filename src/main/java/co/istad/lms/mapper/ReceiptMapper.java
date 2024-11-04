package co.istad.lms.mapper;


import co.istad.lms.domain.Receipt;
import co.istad.lms.features.receipt.dto.ReceiptRequest;
import co.istad.lms.features.receipt.dto.ReceiptResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {


    Receipt toReceipt(ReceiptRequest receiptRequest);

    ReceiptResponse toReceiptResponse(Receipt receipt);

}
