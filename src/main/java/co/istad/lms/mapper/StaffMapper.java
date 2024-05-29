package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Staff toRequest(StaffRequest staffRequest);

    @Mapping(target = "user", source = "user")
    StaffResponse toResponse(Staff staff);

    @Mapping(target = "user", source = "user")
    StaffRequestDetail toResponseDetail(Staff staff);
}
