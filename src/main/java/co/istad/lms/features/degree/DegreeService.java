package co.istad.lms.features.degree;

import co.istad.lms.features.degree.dto.DegreeCreateRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;

public interface DegreeService {
    DegreeDetailResponse createDegree(DegreeCreateRequest degreeRequest);

    DegreeDetailResponse getDegreeByAlias(String alias);

    DegreeDetailResponse getDegreeByLevel(String level);

    DegreeDetailResponse getAllDegree();

    DegreeResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest);

    DegreeResponse updateDegreeByLevel(String level, DegreeUpdateRequest degreeUpdateRequest);

    void deleteDegreeByAlias(String alias);

    void deleteDegreeByLevel(String level);


}
