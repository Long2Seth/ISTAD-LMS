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
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface YearOfStudyMapper {

    YearOfStudy fromYearOfStudyRequest(YearOfStudyRequest yearOfStudyRequest);

    YearOfStudyDetailResponse toYearOfStudyDetailResponse(YearOfStudy yearOfStudy);

    YearOfStudyResponse toYearOfStudyResponse(YearOfStudy yearOfStudy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateYearOfStudyFromRequest(@MappingTarget YearOfStudy yearOfStudy, YearOfStudyUpdateRequest yearOfStudyUpdateRequest);
}
