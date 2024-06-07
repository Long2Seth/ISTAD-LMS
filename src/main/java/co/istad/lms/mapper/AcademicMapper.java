package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Academic;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
import co.istad.lms.features.academic.dto.AcademicResponse;
import co.istad.lms.features.academic.dto.AcademicResponseDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AcademicMapper {

    Academic toRequest(AcademicRequest academicRequest);


    @Mapping(source = "user", target = "user")
    AcademicResponse toResponse(Academic academic);

    @Mapping(source = "user", target = "user")
    AcademicResponseDetail toResponseDetail(Academic academic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "academic.user", source = "academicRequestDetail.user",qualifiedByName = "updateUserDetailFromRequest")
    void updateAcademicFromRequest(@MappingTarget Academic academic, AcademicRequestDetail academicRequestDetail );

}
