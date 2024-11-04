package co.istad.lms.features.curriculum;

import co.istad.lms.features.curriculum.dto.CurriculumRequest;
import co.istad.lms.features.curriculum.dto.CurriculumResponse;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterRequest;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterResponse;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage curriculums
 * @author Long Piseth
 * @since 1.0 (2024)
 */
public interface CurriculumService {


    /**
     * Retrieves a paginated list of all curriculums.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<CurriculumResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<CurriculumResponse> getAllCurriculums(int page, int limit);



    /**
     * Creates a new curriculum.
     *
     * @param curriculumRequest is the request object containing curriculum details for create curriculum
     * @return {@link CurriculumResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    CurriculumResponse createCurriculum(CurriculumRequest curriculumRequest);


    /**
     * Retrieves the details of a curriculum by year and semester.
     *
     * @param year is the unique identifier of curriculum
     * @param semester is the unique identifier of curriculum
     * @return {@link CurriculumResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    SubjectOfSemesterResponse addSubjectToCurriculum(SubjectOfSemesterRequest subjectOfSemesterRequest , String year, String semester);



    /**
     * Retrieves the details of a curriculum by year and semester.
     *
     * @param year is the unique identifier of curriculum
     * @param semester is the unique identifier of curriculum
     * @return {@link CurriculumResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    CurriculumResponse getCurriculumByYearAndSemester( String year, String semester);



    /**
     * Updates an existing curriculum by year and semester.
     *
     * @param curriculumRequest the request object containing the updated curriculum details
     * @param year is the unique identifier of curriculum
     * @param semester is the unique identifier of curriculum
     * @return {@link CurriculumResponse}
     * @since 1.0 (2024)
     */
    CurriculumResponse updateCurriculumBySemesterAndYear(CurriculumRequest curriculumRequest, String year, String semester);



    /**
     * Delete curriculum by  year and semester.
     *
     * @param year is the unique identifier of curriculum
     * @param semester is the unique identifier of curriculum
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteCurriculumBySemesterAndYear(String year, String semester);

}
