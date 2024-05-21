package co.istad.lms.features.shift;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.shift.dto.*;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing shifts.
 */
public interface ShiftService {

    /**
     * Creates a new shift.
     *
     * @param shiftRequest the request object containing details for the new shift
     */
    void createNewShift(ShiftRequest shiftRequest);

    /**
     *
     * @param alias unique name for shift
     * @return return detail of shift
     */
    ShiftDetailResponse getShiftByAlias(String alias);

    /**
     * Retrieves a paginated list of all shifts.
     *
     * @param page the page number to retrieve
     * @param size the number of shifts per page
     * @return a paginated list of shifts
     */
    Page<ShiftDetailResponse> getAllShift(int page, int size);

    /**
     * Updates a shift identified by its alias.
     *
     * @param alias the alias of the shift to be updated
     * @param shiftUpdateRequest the request object containing updated details for the shift
     * @return the updated shift details
     */
    ShiftResponse updateByAlias(String alias, ShiftUpdateRequest shiftUpdateRequest);

    /**
     * Deletes a shift identified by its alias.
     *
     * @param alias the alias of the shift to be deleted
     */
    void deleteShiftByAlias(String alias);

    /**
     * Filters shifts based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto the filter criteria
     * @param page the page number to retrieve
     * @param size the number of shifts per page
     * @return a paginated list of filtered shifts
     */
    Page<ShiftDetailResponse> filterShift(BaseSpecification.FilterDto filterDto, int page, int size);
}
