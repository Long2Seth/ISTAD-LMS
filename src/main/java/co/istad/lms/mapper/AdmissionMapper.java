package co.istad.lms.mapper;

import co.istad.lms.domain.Admission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdmissionMapper {

    Admission fromAdmissionRequest(AdmissionCreateRequest admissionCreateRequest);

    AdmissionResponse toAdmissionResponse(Admission admission);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdmissionFromRequest(@MappingTarget Admission admission, AdmissionUpdateRequest AdmissionRequest);

}
