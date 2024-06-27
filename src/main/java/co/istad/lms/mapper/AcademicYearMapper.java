package co.istad.lms.mapper;

import co.istad.lms.domain.AcademicYear;
import co.istad.lms.domain.Admission;
import co.istad.lms.features.academicyear.dto.AcademicYearDetailResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearRequest;
import co.istad.lms.features.academicyear.dto.AcademicYearResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearUpdateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.util.DateTimeUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AcademicYearMapper {

    @Mapping(target = "academicYear", source = "academicYear", qualifiedByName = "validateAcademicYear")
    AcademicYear fromAcademicYearRequest(AcademicYearRequest academicYearRequest);

    AcademicYearResponse toAcademicYearResponse(AcademicYear academicYear);

    AcademicYearDetailResponse toAcademicYearDetailResponse(AcademicYear academicYear);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAcademicYearFromRequest(@MappingTarget AcademicYear academicYear, AcademicYearUpdateRequest academicYearUpdateRequest);


    @Named("validateAcademicYear")
    default String validateAcademicYear(String academicYear) {
        DateTimeUtil.validateAcademicYear(academicYear);
        return academicYear;
    }
}