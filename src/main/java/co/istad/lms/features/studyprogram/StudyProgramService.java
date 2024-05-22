package co.istad.lms.features.studyprogram;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing study programs.
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface StudyProgramService {

    /**
     * Creates a new study program.
     *
     * @param studyProgramRequest is the request object containing the details of the study program to be created
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createStudyProgram(StudyProgramRequest studyProgramRequest);

    /**
     * Retrieves the details of a study program by its alias.
     *
     * @param alias is the unique name of studyProgram
     * @return {@link StudyProgramDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    StudyProgramDetailResponse getStudyProgramByAlias(String alias);

    /**
     * Retrieves a paginated list of all study programs.
     *
     * @param page is the current page number to retrieve
     * @param size is the number record per page to retrieve
     * @return {@link Page<StudyProgramDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudyProgramDetailResponse> getAllStudyPrograms(int page, int size);

    /**
     * Updates an existing study program by its alias.
     *
     * @param alias                     the alias of the study program to be updated
     * @param studyProgramUpdateRequest the request object containing the updated details of the study program
     * @return {@link StudyProgramResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    StudyProgramResponse updateStudyProgramByAlias(String alias, StudyProgramUpdateRequest studyProgramUpdateRequest);

    /**
     * Deletes a study program by its alias.
     *
     * @param alias is the unique name of studyProgram
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteStudyProgramByAlias(String alias);


    /**
     * Enable studyProgram
     *
     * @param alias is the unique name of studyProgram
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableStudyProgramByAlias(String alias);

    /**
     * Disable studyProgram by alias
     *
     * @param alias is the unique name of studyProgram
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableStudyProgramByAlias(String alias);

    /**
     * @param filterDto is object for request to filter
     * @param page is the current page number to retrieve
     * @param size is the number record per page to retrieve
     * @return {@link Page<StudyProgramDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudyProgramDetailResponse> filterStudyPrograms(BaseSpecification.FilterDto filterDto, int page, int size);
}
