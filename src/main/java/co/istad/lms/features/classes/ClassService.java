package co.istad.lms.features.classes;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.classes.dto.ClassDetailResponse;
import co.istad.lms.features.classes.dto.ClassRequest;
import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.classes.dto.ClassUpdateRequest;
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
     * @param page is the current page number to retrieve
     * @param size is the size of record per page to retrieve
     * @return {@link Page<ClassDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ClassDetailResponse> getAllClasses(int page, int size);

    /**
     * Updates an existing class by its alias.
     *
     * @param alias               is the unique name of class
     * @param classUpdateRequest is the request object containing the updated class details
     * @return {@link ClassResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ClassResponse updateClassByAlias(String alias, ClassUpdateRequest classUpdateRequest);


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
     * @param page      is the current page number
     * @param size      size of record per page to retrieve
     * @return {@link Page<ClassDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ClassDetailResponse> filterClasses(BaseSpecification.FilterDto filterDto, int page, int size);

}
