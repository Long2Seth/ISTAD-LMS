package co.istad.lms.mapper;

import co.istad.lms.domain.Admission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdmissionMapper {

    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    @Mapping(target = "degree", ignore = true)
    Admission fromAdmissionRequest(AdmissionCreateRequest admissionCreateRequest);

    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    AdmissionResponse toAdmissionResponse(Admission admission);


    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    @Mapping(target = "degree", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdmissionFromRequest(@MappingTarget Admission admission, AdmissionUpdateRequest AdmissionRequest);

}
