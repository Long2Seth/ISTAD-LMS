package co.istad.lms.mapper;

import co.istad.lms.domain.Shift;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ShiftMapper {

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    Shift fromShiftRequest(ShiftRequest ShiftRequest);

    ShiftDetailResponse toShiftDetailResponse(Shift Shift);

    ShiftResponse toShiftResponse(Shift Shift);


    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShiftFromRequest(@MappingTarget Shift Shift, ShiftUpdateRequest ShiftUpdateRequest);
}
