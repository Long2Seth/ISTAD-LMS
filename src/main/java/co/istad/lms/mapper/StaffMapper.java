package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import org.mapstruct.*;
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StaffMapper {

    Staff toRequest(StaffRequest staffRequest);

    @Mapping(target = "user", source = "user")
    StaffResponse toResponse(Staff staff);

    Staff toRequestDetail(StaffRequestDetail staffRequestDetail);

    @Mapping(target = "user", source = "user")
    StaffResponseDetail toResponseDetail(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "staff.user", source = "staffRequestDetail.user" , qualifiedByName = "updateUserDetailFromRequest")
    void updateStaffFromRequest(@MappingTarget Staff staff, StaffRequestDetail staffRequestDetail);

}
