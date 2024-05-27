package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface YearOfStudyMapper {

    @Mapping(target = "subjects",ignore = true)
    YearOfStudy fromYearOfStudyRequest(YearOfStudyRequest yearOfStudyRequest);

    YearOfStudyDetailResponse toYearOfStudyDetailResponse(YearOfStudy yearOfStudy);

    YearOfStudyResponse toYearOfStudyResponse(YearOfStudy yearOfStudy);

    @Mapping(target = "subjects",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateYearOfStudyFromRequest(@MappingTarget YearOfStudy yearOfStudy, YearOfStudyUpdateRequest yearOfStudyUpdateRequest);
}
