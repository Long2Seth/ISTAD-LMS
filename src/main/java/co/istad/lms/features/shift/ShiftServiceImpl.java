package co.istad.lms.features.shift;

import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ShiftServiceImpl implements ShiftService{
    @Override
    public void createNewShift() {

    }

    @Override
    public ShiftDetailResponse getShiftByAlias(String alias) {
        return null;
    }

    @Override
    public Page<ShiftDetailResponse> getAllShift(int page, int size) {
        return null;
    }

    @Override
    public void deleteShiftByAlias(String alias) {

    }
}
