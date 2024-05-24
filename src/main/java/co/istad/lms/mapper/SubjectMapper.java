package co.istad.lms.mapper;



import co.istad.lms.domain.Subject;
import co.istad.lms.features.subject.dto.SubjectDetailResponse;
import co.istad.lms.features.subject.dto.SubjectRequest;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.subject.dto.SubjectUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject fromDegreeRequest(SubjectRequest degreeCreateRequest);

    SubjectDetailResponse toSubjectDetailResponse(Subject subject);

    SubjectResponse toSubjectResponse(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubjectFromRequest(@MappingTarget Subject subject, SubjectUpdateRequest subjectUpdateRequest);

}
