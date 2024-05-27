package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DegreeMapper {

    Degree fromDegreeRequest(DegreeRequest degreeRequest);

    DegreeDetailResponse toDegreeDetailResponse(Degree degree);

    DegreeResponse toDegreeResponse(Degree degree);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDegreeFromRequest(@MappingTarget Degree degree, DegreeUpdateRequest degreeUpdateRequest);

}
