package co.istad.lms.features.shift;

import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import org.springframework.data.domain.Page;

public interface ShiftService {

    void createNewShift();

    ShiftDetailResponse getShiftByAlias(String alias);

    Page<ShiftDetailResponse> getAllShift(int page, int size);

    void deleteShiftByAlias(String alias);
}
