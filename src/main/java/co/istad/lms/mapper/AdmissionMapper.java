package co.istad.lms.mapper;

import co.istad.lms.domain.StudentAdmission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdmissionMapper {

    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    @Mapping(target = "degree", ignore = true)
    StudentAdmission fromAdmissionRequest(AdmissionCreateRequest admissionCreateRequest);

    AdmissionResponse toAdmissionResponse(StudentAdmission admission);


    AdmissionDetailResponse toAdmissionDetailResponse(StudentAdmission admission);


    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    @Mapping(target = "degree", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdmissionFromRequest(@MappingTarget StudentAdmission admission, AdmissionUpdateRequest AdmissionRequest);

}
