package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.staff.dto.*;
import org.mapstruct.*;
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StaffMapper {

    Staff toRequest(StaffRequest staffRequest);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    StaffResponse toResponse(Staff staff);



    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    StaffResponseDetail toResponseDetail(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStaffFromRequest(@MappingTarget Staff staff, StaffRequestUpdate staffRequestUpdate);

}
