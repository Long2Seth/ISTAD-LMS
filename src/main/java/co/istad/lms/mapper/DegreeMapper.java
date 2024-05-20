package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.features.degree.dto.DegreeCreateRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DegreeMapper {
    Degree fromDegreeRequest(DegreeCreateRequest degreeCreateRequest);
    DegreeDetailResponse toDegreeDetailResponse(Degree degree);
    DegreeResponse toDegreeResponse(Degree degree);
}
