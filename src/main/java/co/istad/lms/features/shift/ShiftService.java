package co.istad.lms.features.shift;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.shift.dto.*;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing shifts.
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface ShiftService {

    /**
     * Creates a new shift.
     *
     * @param shiftRequest is  the request object containing details for the new shift
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createShift(ShiftRequest shiftRequest);

    /**
     * @param alias is the unique name for shift
     * @return {@link ShiftDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ShiftDetailResponse getShiftByAlias(String alias);

    /**
     * Retrieves a paginated list of all shifts.
     *
     * @param page is the page number to retrieve
     * @param size is the number of shifts per page
     * @return {@link Page<ShiftDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ShiftDetailResponse> getAllShifts(int page, int size);

    /**
     * Updates a shift identified by its alias.
     *
     * @param alias              is the unique name of shift
     * @param shiftUpdateRequest the request object containing updated details for the shift
     * @return {@link ShiftResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ShiftResponse updateByAlias(String alias, ShiftUpdateRequest shiftUpdateRequest);

    /**
     * Deletes a shift by alias
     *
     * @param alias is the unique name of shift
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteShiftByAlias(String alias);


    /**
     * Enable shift by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableShiftByAlias(String alias);

    /**
     * Disable shift by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableShiftByAlias(String alias);

    /**
     * Filters shifts based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto the filter criteria
     * @param page      the page number to retrieve
     * @param size      the number of shifts per page
     * @return {@link  Page<ShiftDetailResponse> }
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ShiftDetailResponse> filterShifts(BaseSpecification.FilterDto filterDto, int page, int size);
}
