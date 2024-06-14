package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Academic;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.academic.dto.*;
import co.istad.lms.features.admin.dto.AdminRequestUpdate;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AcademicMapper {

    Academic toRequest(AcademicRequest academicRequest);


    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    AcademicResponse toResponse(Academic academic);


    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    AcademicResponseDetail toResponseDetail(Academic academic);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAcademicFromRequest(@MappingTarget Academic academic, AcademicRequestUpdate academicRequestUpdate);

}
