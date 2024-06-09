package co.istad.lms.mapper;


import co.istad.lms.domain.Graduation;
import co.istad.lms.features.graduation.dto.GraduationRequest;
import co.istad.lms.features.graduation.dto.GraduationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GraduationMapper {

    Graduation toRequest(GraduationRequest graduationRequest );

    GraduationResponse toResponse(Graduation graduation);


}
