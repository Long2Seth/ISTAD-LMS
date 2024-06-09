package co.istad.lms.mapper;

import co.istad.lms.domain.Admission;
import co.istad.lms.domain.StudentAdmission;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdmissionMapper {

    Admission fromAdmissionRequest(AdmissionRequest admissionCreateRequest);

    AdmissionResponse toAdmissionResponse(Admission admission);

    AdmissionDetailResponse toAdmissionDetailResponse(Admission admission);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdmissionFromRequest(@MappingTarget Admission admission, AdmissionUpdateRequest AdmissionRequest);

}
