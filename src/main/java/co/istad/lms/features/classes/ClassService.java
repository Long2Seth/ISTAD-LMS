package co.istad.lms.features.classes;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.classes.dto.*;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage Classes
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface ClassService {

    /**
     * Create new Classes
     *
     * @param classRequest is the object that contain information to create new Class
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createClass(ClassRequest classRequest);

    /**
     * Retrieves the details of a class by its alias.
     *
     * @param alias is the unique name of class
     * @return {@link ClassDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ClassDetailResponse getClassByAlias(String alias);

    /**
     * Retrieves a paginated list of all classes.
     *
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the pageSize of record per pageNumber to retrieve
     * @return {@link Page<ClassDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ClassDetailResponse> getAllClasses(int pageNumber, int pageSize);

    /**
     * Updates an existing class by its alias.
     *
     * @param alias               is the unique name of class
     * @param classUpdateRequest is the request object containing the updated class details
     * @return {@link ClassDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ClassDetailResponse updateClassByAlias(String alias, ClassUpdateRequest classUpdateRequest);


    /**
     * Deletes a class by its alias.
     *
     * @param alias is the unique name of class
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteClassByAlias(String alias);

    /**
     * Enable class by alias
     *
     * @param alias is the unique name of class
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableClassByAlias(String alias);

    /**
     * Disable class by alias
     *
     * @param alias is the unique name of class
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableClassByAlias(String alias);

    /**
     * Filters class based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<ClassDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ClassDetailResponse> filterClasses(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);

    /**
     *
     * @param alias is the unique name of class
     * @param classAddSubjectRequest contain information of student to add
     * @return {@link ClassDetailResponse}
     */
    ClassDetailResponse addStudent(String alias, ClassAddStudentRequest classAddSubjectRequest);

    /**
     *
     * @param alias is the unique name of class
     * @param uuid is the unique identify of student
     */
    void deleteStudent(String alias,String uuid);

}
