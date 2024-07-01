package co.istad.lms.mapper;

import co.istad.lms.domain.Faculty;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import co.istad.lms.util.MediaUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FacultyMapper {


    Faculty fromFacultyRequest(FacultyRequest facultyRequest);

    @Mapping(source = "logo",target = "logo",qualifiedByName = "getLogoUrl")
    FacultyDetailResponse toFacultyDetailResponse(Faculty faculty);
    @Named("getLogoUrl")
    default String getLogoUrl(String logo) {

        if (logo != null && !logo.trim().isEmpty()) {
            return MediaUtil.getUrl(logo);
        } else {
            return null;
        }
    }

    FacultyResponse toFacultyResponse(Faculty faculty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "logo",ignore = true)
    void updateFacultyFromRequest(@MappingTarget Faculty faculty, FacultyUpdateRequest facultyUpdateRequest);

}
