package co.istad.lms.features.lecture;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import org.springframework.data.domain.Page;

public interface LectureService {

    /**
     * Creates a new lecture.
     *
     * @param lectureRequest is the request object containing lecture details
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void createLecture(LectureRequest lectureRequest);

    /**
     * Retrieves the details of a lecture by its alias.
     *
     * @param alias is the unique name of lecture
     * @return {@link LectureDetailResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    LectureDetailResponse getLectureByAlias(String alias);

    /**
     * Retrieves a paginated list of all lectures.
     *
     * @param page is the current page number to retrieve
     * @param size is the size of record per page to retrieve
     * @return {@link Page<LectureDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<LectureDetailResponse> getAllLectures(int page, int size);

    /**
     * Updates an existing lecture by its alias.
     *
     * @param alias                is the unique name of lecture
     * @param lectureUpdateRequest is the request object containing the updated lecture details
     * @return {@link LectureDetailResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    LectureDetailResponse updateLectureByAlias(String alias, LectureUpdateRequest lectureUpdateRequest);

    /**
     * Deletes a lecture by its alias.
     *
     * @param alias is the unique name of lecture
     * @author Nouth chanraksa
     * @since 1.0 (2024)
     */
    void deleteLectureByAlias(String alias);


    /**
     * Enable lecture by alias
     *
     * @param alias is the unique name of lecture
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void enableLectureByAlias(String alias);

    /**
     * Disable lecture by alias
     *
     * @param alias is the unique name of lecture
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void disableLectureByAlias(String alias);

    /**
     * Filters lecture based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param page      is the current page number
     * @param size      size of record per page to retrieve
     * @return {@link Page<LectureDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<LectureDetailResponse> filterLectures(BaseSpecification.FilterDto filterDto, int page, int size);

}