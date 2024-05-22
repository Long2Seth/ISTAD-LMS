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
 */
public interface StudyProgramService {

    /**
     * Creates a new study program.
     *
     * @param studyProgramRequest the request object containing the details of the study program to be created
     */
    void createStudyProgram(StudyProgramRequest studyProgramRequest);

    /**
     * Retrieves the details of a study program by its alias.
     *
     * @param alias the alias of the study program
     * @return the detailed response object of the study program
     */
    StudyProgramDetailResponse getStudyProgramByAlias(String alias);

    /**
     * Retrieves a paginated list of all study programs.
     *
     * @param page the page number to retrieve
     * @param size the number of study programs per page
     * @return a paginated list of study programs
     */
    Page<StudyProgramDetailResponse> getAllStudyPrograms(int page, int size);

    /**
     * Updates an existing study program by its alias.
     *
     * @param alias the alias of the study program to be updated
     * @param studyProgramUpdateRequest the request object containing the updated details of the study program
     * @return the response object containing the updated details of the study program
     */
    StudyProgramResponse updateStudyProgramByAlias(String alias, StudyProgramUpdateRequest studyProgramUpdateRequest);

    /**
     * Deletes a study program by its alias.
     *
     * @param alias the alias of the study program to be deleted
     */
    void deleteStudyProgramByAlias(String alias);


    /**
     *
     * @param filterDto is object for request to filter
     * @param page  number of current page
     * @param size size of page
     * @return return detail of studyProgram
     */
    Page<StudyProgramDetailResponse> filterStudyProgram(BaseSpecification.FilterDto filterDto, int page, int size);
}
