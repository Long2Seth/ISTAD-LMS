package co.istad.lms.features.academicyear;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.academicyear.dto.AcademicYearDetailResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearRequest;
import co.istad.lms.features.academicyear.dto.AcademicYearUpdateRequest;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage academicYear
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface AcademicYearService {

    /**
     * Creates a new academicYear.
     *
     * @param academicYearRequest is the request object containing academicYear details for create
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createAcademicYear(AcademicYearRequest academicYearRequest);

    /**
     * Retrieves the details of an academic by its alias.
     *
     * @param alias is the unique identifier of academic
     * @return {@link AcademicYearDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    AcademicYearDetailResponse getAcademicYearByAlias(String alias);

    /**
     * Retrieves a paginated list of all academicYear.
     *
     * @param pageNumber is the pageNumber number to retrieve
     * @param pageSize is the pageSize of the pageNumber to retrieve
     * @return * @return {@link Page<AcademicYearDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AcademicYearDetailResponse> getAllAcademicYears(int pageNumber, int pageSize);

    /**
     * Updates an existing academicYear
     *
     * @param alias    is the unique identifier of academicYear
     * @param academicYearUpdateRequest the request object containing the updated academicYear details
     * @return {@link AcademicYearDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    AcademicYearDetailResponse updateAcademicYear(String alias, AcademicYearUpdateRequest academicYearUpdateRequest);

    /**
     * Delete academicYear by  alias.
     *
     * @param alias the unique identifier of academicYear
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteAcademicYear(String alias);

    /**
     * Disables  academicYear by  alias.
     *
     * @param alias is the unique identifier of academicYear
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableAcademicYearByAlias(String alias);

    /**
     * Enables an academicYear by its alias.
     *
     * @param alias is the unique identifier of academicYear
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableAcademicYearByAlias(String alias);


    /**
     * Filters academicYear based on the provided criteria.
     *
     * @param filterDto is the object use for filter any column, any operation
     * @param pageNumber      is the pageNumber number of current to retrieve
     * @param pageSize      is the pageSize of record per pageNumber
     * @return {@link  Page<AcademicYearDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AcademicYearDetailResponse> filterAcademicYear(BaseSpecification.FilterDto filterDto, int pageNumber,
                                                    int pageSize);

}

