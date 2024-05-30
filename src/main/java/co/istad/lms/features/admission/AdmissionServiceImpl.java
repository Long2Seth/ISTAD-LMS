// AdmissionServiceImpl.java

package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.features.degree.DegreeRepository;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.telegrambot.TelegramBotService;
import co.istad.lms.mapper.AdmissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final AdmissionMapper admissionMapper;
    private final DegreeRepository degreeRepository;
    private final ShiftRepository shiftRepository;
    private final StudyProgramRepository studyProgramRepository;
    private final BaseSpecification<Admission> baseSpecification;

    @Override
    public void createAdmission(AdmissionRequest admissionRequest) {

       Admission admission=admissionMapper.fromAdmissionRequest(admissionRequest);

       admissionRepository.save(admission);
    }

    @Override
    public AdmissionDetailResponse getAdmissionByUuid(String uuid) {

        //find admission in database by uuid
        Admission admission = admissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ", uuid)));

        //save to database and return AdmissionDetail
        return admissionMapper.toAdmissionDetailResponse(admission);
    }

    @Override
    public Page<AdmissionResponse> getAllAdmissions(int page, int size) {

        //create sort order
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        //find all admission in database
        Page<Admission> admissionsPage = admissionRepository.findAll(pageRequest);

        //map entity to database and return AdmissionDetail
        return admissionsPage.map(admissionMapper::toAdmissionResponse);
    }

    @Override
    public AdmissionDetailResponse updateAdmission(String admissionUuid,
                                                   AdmissionUpdateRequest admissionUpdateRequest) {

        //find admission by uuid in database
        Admission admission = admissionRepository.findByUuid(admissionUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission  = %s has not been found ! ", admissionUuid)));

        //map data from DTO to entity
        admissionMapper.updateAdmissionFromRequest(admission, admissionUpdateRequest);

        //save to database
        admissionRepository.save(admission);

        //return admissionResponse to controller
        return admissionMapper.toAdmissionDetailResponse(admission);
    }

    @Override
    public void deleteAdmission(String admissionUuid) {

        //validate admission by uuid
        Admission admission = admissionRepository.findByUuid(admissionUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", admissionUuid)));

        //delete from database
        admissionRepository.delete(admission);
    }

    @Override
    public void disableAdmissionByUuid(String admissionUuid) {

        //validate from dto with uuid
        Admission admission = admissionRepository.findByUuid(admissionUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", admissionUuid)));

        //set isDelete to true (disable)
        admission.setIsDeleted(true);

        //save to database
        admissionRepository.save(admission);
    }

    @Override
    public void enableAdmissionByUuid(String admissionUuid) {

        //validate from dto by uuid
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admission = %s has not been found ! ", admissionUuid)));

        //set isDeleted to false(enable)
        admission.setIsDeleted(false);

        //save to database
        admissionRepository.save(admission);

    }

    @Override
    public Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Admission entities based on the criteria provided
        Specification<Admission> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Admission> admissionsPage = admissionRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return admissionsPage.map(admissionMapper::toAdmissionDetailResponse);

    }
}
