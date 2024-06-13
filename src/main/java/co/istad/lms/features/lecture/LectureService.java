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
     * Retrieves the details of a lecture by its uuid.
     *
     * @param uuid is the unique name of lecture
     * @return {@link LectureDetailResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    LectureDetailResponse getLectureByUuid(String uuid);

    /**
     * Retrieves a paginated list of all lectures.
     *
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the pageSize of record per pageNumber to retrieve
     * @return {@link Page<LectureDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<LectureDetailResponse> getAllLectures(int pageNumber, int pageSize);

    /**
     * Updates an existing lecture by its uuid.
     *
     * @param uuid                is the unique name of lecture
     * @param lectureUpdateRequest is the request object containing the updated lecture details
     * @return {@link LectureDetailResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    LectureDetailResponse updateLectureByUuid(String uuid, LectureUpdateRequest lectureUpdateRequest);

    /**
     * Deletes a lecture by its uuid.
     *
     * @param uuid is the unique name of lecture
     * @author Nouth chanraksa
     * @since 1.0 (2024)
     */
    void deleteLectureByUuid(String uuid);


    /**
     * Enable lecture by uuid
     *
     * @param uuid is the unique name of lecture
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void enableLectureByUuid(String uuid);

    /**
     * Disable lecture by uuid
     *
     * @param uuid is the unique name of lecture
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void disableLectureByUuid(String uuid);

    /**
     * Filters lecture based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<LectureDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<LectureDetailResponse> filterLectures(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);

}