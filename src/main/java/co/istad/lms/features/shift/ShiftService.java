package co.istad.lms.features.shift;

import co.istad.lms.features.shift.dto.*;
import org.springframework.data.domain.Page;

public interface ShiftService {

    void createNewShift(ShiftRequest shiftRequest);

    ShiftDetailResponse getShiftByAlias(String alias);

    Page<ShiftDetailResponse> getAllShift(int page, int size);

    ShiftUpdateResponse updateByAlias(String alias, ShiftUpdateRequest shiftUpdateRequest);



    void deleteShiftByAlias(String alias);
}
