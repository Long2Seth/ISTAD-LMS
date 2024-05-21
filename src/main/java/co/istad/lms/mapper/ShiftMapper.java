package co.istad.lms.mapper;

import co.istad.lms.domain.Shift;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import co.istad.lms.features.shift.dto.ShiftUpdateResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift fromShiftRequest(ShiftRequest ShiftRequest);

    ShiftDetailResponse toShiftDetailResponse(Shift Shift);

    ShiftDetailResponse toShiftResponse(Shift Shift);

    ShiftUpdateResponse toShiftUpdateResponse(Shift Shift);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShiftFromRequest(@MappingTarget Shift Shift, ShiftUpdateRequest ShiftUpdateRequest);
}
