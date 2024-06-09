package co.istad.lms.features.shift;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.prefs.BackingStoreException;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
    private final BaseSpecification<Shift> baseSpecification;

    @Override
    public void createShift(ShiftRequest shiftRequest) {

        //validate shift from DTO by alias
        if (shiftRepository.existsByAlias(shiftRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Shift = %s already exists.", shiftRequest.alias()));
        }

        //map from DTO to entity
        Shift shift = shiftMapper.fromShiftRequest(shiftRequest);

        //set isDeleted to false(enable)
        shift.setIsDeleted(false);

        //save to database
        shiftRepository.save(shift);
    }

    @Override
    public ShiftDetailResponse getShiftByAlias(String alias) {

        //validate shift from DTO by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found.", alias)));

        //return shift detail
        return shiftMapper.toShiftDetailResponse(shift);
    }

    @Override
    public Page<ShiftDetailResponse> getAllShifts(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all shift in database
        Page<Shift> shift = shiftRepository.findAll(pageRequest);

        //map entity to DTO and return
        return shift.map(shiftMapper::toShiftDetailResponse);
    }

    @Override
    public ShiftDetailResponse updateByAlias(String alias, ShiftUpdateRequest shiftUpdateRequest) {

        //validate shift from DTO by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found.", alias)));

        //check null alias from DTO
        if (shiftUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(shiftUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (shiftRepository.existsByAlias(shiftUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Shift = %s already exist.", shiftUpdateRequest.alias()));
                }
            }
        }

        //map from DTO to entity
        shiftMapper.updateShiftFromRequest(shift, shiftUpdateRequest);

        //save to database
        shiftRepository.save(shift);

        //return shift detail
        return shiftMapper.toShiftDetailResponse(shift);
    }

    @Override
    public void deleteShiftByAlias(String alias) {

        //validate shift from DTO by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found.", alias)));

        //delete from database
        shiftRepository.delete(shift);
    }

    @Override
    public void enableShiftByAlias(String alias) {

        //validate degree from dto by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        shift.setIsDeleted(false);

        //save to database
        shiftRepository.save(shift);
    }

    @Override
    public void disableShiftByAlias(String alias) {

        //validate shift from dto by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        shift.setIsDeleted(true);

        //save to database
        shiftRepository.save(shift);

    }

    @Override
    public Page<ShiftDetailResponse> filterShifts(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Shift entities based on the criteria provided
        Specification<Shift> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Shift> shifts = shiftRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return shifts.map(shiftMapper::toShiftDetailResponse);
    }

    @Override
    public void publicShiftByAlias(String alias) {

        //validate shift from dto by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found ! ", alias)));

        //set isDeleted to true(public)
        shift.setIsDraft(false);

        //save to database
        shiftRepository.save(shift);
    }

    @Override
    public void draftShiftByAlias(String alias) {

        //validate shift from dto by alias
        Shift shift = shiftRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found ! ", alias)));

        //set isDeleted to true(draft)
        shift.setIsDraft(true);

        //save to database
        shiftRepository.save(shift);
    }
}
