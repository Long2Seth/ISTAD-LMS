package co.istad.lms.features.shift;

import co.istad.lms.domain.Shift;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import co.istad.lms.mapper.ShiftMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService{

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    @Override
    public void createNewShift(ShiftRequest shiftRequest) {

        if (shiftRepository.existsByAlias(shiftRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Shift = %s already exists.", shiftRequest.alias()));
        }

        Shift shift =shiftMapper.fromShiftRequest(shiftRequest);
        shift.setIsDeleted(false);
        shiftRepository.save(shift);
    }

    @Override
    public ShiftDetailResponse getShiftByAlias(String alias) {
        Shift shift = shiftRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Shift = %s was not found.", alias)));

        return shiftMapper.toShiftDetailResponse(shift);
    }

    @Override
    public Page<ShiftDetailResponse> getAllShift(int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);
        Page<Shift> shift = shiftRepository.findAll(pageRequest);

        return shift.map(shiftMapper::toShiftDetailResponse);
    }

    @Override
    public ShiftResponse updateByAlias(String alias, ShiftUpdateRequest shiftUpdateRequest) {
        Shift shift = shiftRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Shift = %s was not found.", alias)));

        shiftMapper.updateShiftFromRequest(shift, shiftUpdateRequest);
        shiftRepository.save(shift);

        return shiftMapper.toShiftResponse(shift);
    }

    @Override
    public void deleteShiftByAlias(String alias) {

        Shift shift = shiftRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Shift = %s was not found.", alias)));

        shiftRepository.delete(shift);
    }
}
