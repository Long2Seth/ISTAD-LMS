package co.istad.lms.features.subject;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.subject.dto.SubjectDetailResponse;
import co.istad.lms.features.subject.dto.SubjectRequest;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.subject.dto.SubjectUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage subjects
 *
 * @author Nouth Chanreaksa
 * @since 1.0 (2024)
 */
public interface SubjectService {

    /**
     * Creates a new subject.
     *
     * @param subjectRequest is the request object containing subject details
     * @author Nouth Chanreaksa
     * @since 1.0 (2024)
     */
    void createSubject(SubjectRequest subjectRequest);

    /**
     * Retrieves the details of a subject by its alias.
     *
     * @param alias is the unique name of subject
     * @return {@link SubjectDetailResponse}
     * @author Nouth Chanraeksa
     * @since 1.0 (2024)
     */
    SubjectDetailResponse getSubjectByAlias(String alias);

    /**
     * Retrieves a paginated list of all subject.
     *
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the pageSize of record per pageNumber to retrieve
     * @return {@link Page<SubjectDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<SubjectDetailResponse> getAllSubject(int pageNumber, int pageSize);

    /**
     * Updates an existing subject by its alias.
     *
     * @param alias               is the unique name of subject
     * @param subjectUpdateRequest is the request object containing the updated subject details
     * @return {@link SubjectDetailResponse}
     * @author Nouth Chanreaksa
     * @since 1.0 (2024)
     */
    SubjectDetailResponse updateSubjectByAlias(String alias, SubjectUpdateRequest subjectUpdateRequest);

    /**
     * Deletes a subject by its alias.
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void deleteSubjectByAlias(String alias);


    /**
     * Enable subject by alias
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void enableSubjectByAlias(String alias);

    /**
     * Disable subject by alias
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void disableSubjectByAlias(String alias);

    /**
     * public subject by alias
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void publicSubjectByAlias(String alias);

    /**
     * draft subject by alias
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void draftSubjectByAlias(String alias);

    /**
     * Filters subject based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<SubjectDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<SubjectDetailResponse> filterSubject(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);
}

