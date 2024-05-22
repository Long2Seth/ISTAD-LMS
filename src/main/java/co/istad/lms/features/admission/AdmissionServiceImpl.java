// AdmissionServiceImpl.java

package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.features.degree.DegreeRepository;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.mapper.AdmissionMapper;
import lombok.RequiredArgsConstructor;
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
    public void createAdmission(AdmissionCreateRequest admissionCreateRequest) {

        //validate degree by degree alias
        Degree degree = degreeRepository.findByAlias(admissionCreateRequest.degreeAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("degree = %s was not found", admissionCreateRequest.degreeAlias())));

        //validate shift by shift alias
        Shift shift = shiftRepository.findByAlias(admissionCreateRequest.shiftAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("shift = %s was not found", admissionCreateRequest.shiftAlias())));

        //validate
        StudyProgram studyProgram = studyProgramRepository.findByAlias(admissionCreateRequest.studyProgramAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("studyProgram = %s was not found", admissionCreateRequest.studyProgramAlias())));

        //map from DTO to entity
        Admission admission = admissionMapper.fromAdmissionRequest(admissionCreateRequest);

        //generate uuid for admission
        admission.setUuid(UUID.randomUUID().toString());

        //set shift to entity
        admission.setShift(shift);

        //set degree to entity
        admission.setDegree(degree);

        // studyProgram to entity
        admission.setStudyProgram(studyProgram);

        //save to database
        admissionRepository.save(admission);
    }

    @Override
    public AdmissionDetailResponse getAdmissionByUuid(String uuid) {

        //find admission in database by uuid
        Admission admission = admissionRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Admission = %s doesn't exist ! ", uuid)));

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
    public AdmissionResponse updateAdmission(String admissionUuid, AdmissionUpdateRequest admissionUpdateRequest) {

        //find admission by uuid in database
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Admission  = %s has not been found ! ", admissionUuid)));

        //map data from DTO to entity
        admissionMapper.updateAdmissionFromRequest(admission, admissionUpdateRequest);

        //validate degree from dto with entity
        //if the same, don't update
        if (admissionUpdateRequest.degreeAlias() != null &&
                !admissionUpdateRequest.degreeAlias().equals(admission.getDegree().getAlias())) {

            //find degree in database with uuid
            Degree degree = degreeRepository.findByAlias(admissionUpdateRequest.degreeAlias())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("degree = %s was not found", admissionUpdateRequest.degreeAlias())));

            //set new degree
            admission.setDegree(degree);
        }

        //validate shift from dto with entity
        //if the same, don't update
        if (admissionUpdateRequest.shiftAlias() != null &&
                !admissionUpdateRequest.shiftAlias().equals(admission.getShift().getAlias())) {

            //find shift in database with uuid
            Shift shift = shiftRepository.findByAlias(admissionUpdateRequest.shiftAlias())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("shift = %s was not found", admissionUpdateRequest.shiftAlias())));

            //set new shift
            admission.setShift(shift);
        }

        //validate studyProgram from dto with entity
        //if the same, don't update
        if (admissionUpdateRequest.studyProgramAlias() != null &&
                !admissionUpdateRequest.studyProgramAlias().equals(admission.getStudyProgram().getAlias())) {

            //find studyProgram in database by uuid
            StudyProgram studyProgram = studyProgramRepository.findByAlias(admissionUpdateRequest.studyProgramAlias())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("studyProgram = %s was not found", admissionUpdateRequest.studyProgramAlias())));

           //set new studyProgram
            admission.setStudyProgram(studyProgram);
        }

        //save to database
        admissionRepository.save(admission);

        //return admissionResponse to controller
        return admissionMapper.toAdmissionResponse(admission);
    }

    @Override
    public void deleteAdmission(String admissionUuid) {

        //validate admission by uuid
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Admission = %s has not been found ! ", admissionUuid)));

        //delete from database
        admissionRepository.delete(admission);
    }

    @Override
    public void disableAdmissionByUuid(String admissionUuid) {

        //validate from dto with uuid
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admission = %s has not been found ! ", admissionUuid)));

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
