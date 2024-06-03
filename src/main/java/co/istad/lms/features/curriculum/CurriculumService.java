package co.istad.lms.features.curriculum;

import co.istad.lms.features.curriculum.dto.CurriculumRequest;
import co.istad.lms.features.curriculum.dto.CurriculumResponse;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterRequest;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterResponse;
import org.springframework.data.domain.Page;

public interface CurriculumService {

    Page<CurriculumResponse> getAllCurriculums(int page, int limit);
    CurriculumResponse createCurriculum(CurriculumRequest curriculumRequest);
    SubjectOfSemesterResponse addSubjectToCurriculum(SubjectOfSemesterRequest subjectOfSemesterRequest , String year, String semester);
    CurriculumResponse getCurriculumByYearAndSemester( String year, String semester);
    CurriculumResponse updateCurriculumBySemesterAndYear(CurriculumRequest curriculumRequest, String year, String semester);
    void deleteCurriculumBySemesterAndYear(String year, String semester);

}
