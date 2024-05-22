package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyUpdateRequest;
import org.springframework.data.domain.Page;

public interface YearOfStudyService {

    void createNewYearOfStudy(YearOfStudyRequest yearOfStudyRequest);

    YearOfStudyDetailResponse getYearOfStudyByAlias(String alias);

    Page<YearOfStudyDetailResponse> getAllYearOfStudies(int page, int size);

    YearOfStudyResponse updateYearOfStudyByAlias(String alias, YearOfStudyUpdateRequest yearOfStudyUpdateRequest);

    void deleteYearOfStudyByAlias(String alias);

    Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int page, int size);
}
