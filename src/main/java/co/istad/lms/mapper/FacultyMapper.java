package co.istad.lms.mapper;

import co.istad.lms.domain.Faculty;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    Faculty fromFacultyRequest(FacultyRequest facultyRequest);

    FacultyDetailResponse toFacultyDetailResponse(Faculty faculty);

    FacultyResponse toFacultyResponse(Faculty faculty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFacultyFromRequest(@MappingTarget Faculty faculty, FacultyUpdateRequest facultyUpdateRequest);

}
