package co.istad.lms.mapper;


import co.istad.lms.domain.Curriculum;
import co.istad.lms.features.curriculum.dto.CurriculumRequest;
import co.istad.lms.features.curriculum.dto.CurriculumResponse;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurriculumMapper {

    CurriculumResponse toCurriculumResponse(Curriculum curriculum);

    Curriculum toCurriculum(CurriculumRequest curriculumRequest);

    SubjectOfSemesterResponse toSubjectOfSemesterResponse(Curriculum curriculum);
}
