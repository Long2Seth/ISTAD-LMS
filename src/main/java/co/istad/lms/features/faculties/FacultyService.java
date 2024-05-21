package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import org.springframework.data.domain.Page;

public interface FacultyService {

    void createNewFaculty(FacultyRequest facultyRequest);

    FacultyDetailResponse getFacultyByAlias(String alias);

    Page<FacultyDetailResponse> getAllFaculties(int page, int size);

    FacultyResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest);

    void deleteFacultyByAlias(String alias);

    Page<FacultyDetailResponse> filter(BaseSpecification.FilterDto filterDto, int page, int size);
}
