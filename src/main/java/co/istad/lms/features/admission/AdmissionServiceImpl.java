// AdmissionServiceImpl.java

package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.features.admission.dto.*;
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

    private final BaseSpecification<Admission> baseSpecification;

    @Override
    public void createAdmission(AdmissionRequest admissionRequest) {

        //map form DTO to entity
        Admission admission=admissionMapper.fromAdmissionRequest(admissionRequest);

        //set uuid to admission
        admission.setUuid(UUID.randomUUID().toString());

        admission.setIsDeleted(false);

        //save to database
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
    public Page<AdmissionDetailResponse> getAllAdmissions(int pageNumber, int pageSize) {

        //create sort order
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        //find all admission in database
        Page<Admission> admissionsPage = admissionRepository.findAll(pageRequest);

        //map entity to database and return AdmissionDetail
        return admissionsPage.map(admissionMapper::toAdmissionDetailResponse);
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
        Admission admission = admissionRepository.findByUuid(admissionUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", admissionUuid)));

        //set isDeleted to false(enable)
        admission.setIsDeleted(false);

        //save to database
        admissionRepository.save(admission);

    }

    @Override
    public void updateAdmissionStatus(String uuid, AdmissionUpdateStatusRequest admissionUpdateStatusRequest) {

        Admission admission= admissionRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Admission = %s has not been found",uuid)));

        //set new status
        admission.setStatus(admissionUpdateStatusRequest.status());

        //save to database
        admissionRepository.save(admission);

    }

    @Override
    public Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Admission entities based on the criteria provided
        Specification<Admission> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Admission> admissionsPage = admissionRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return admissionsPage.map(admissionMapper::toAdmissionDetailResponse);

    }
}
