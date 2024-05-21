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

        Degree degree = degreeRepository.findByAlias(admissionCreateRequest.degreeAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("degree = %s was not found", admissionCreateRequest.degreeAlias())));

        Shift shift = shiftRepository.findByAlias(admissionCreateRequest.shiftAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("shift = %s was not found", admissionCreateRequest.shiftAlias())));

        StudyProgram studyProgram = studyProgramRepository.findByAlias(admissionCreateRequest.studyProgramAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("studyProgram = %s was not found", admissionCreateRequest.studyProgramAlias())));

        Admission admission = admissionMapper.fromAdmissionRequest(admissionCreateRequest);
        admission.setUuid(UUID.randomUUID().toString()); // Generate UUID
        admission.setShift(shift);
        admission.setDegree(degree);
        admission.setStudyProgram(studyProgram);
        admissionRepository.save(admission);
    }

    @Override
    public AdmissionDetailResponse getAdmissionByUuid(String uuid) {
        Admission admission = admissionRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Admission = %s doesn't exist ! ", uuid)));

        return admissionMapper.toAdmissionDetailResponse(admission);
    }

    @Override
    public Page<AdmissionResponse> getAllAdmissions(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Admission> admissionsPage = admissionRepository.findAll(pageRequest);

        return admissionsPage.map(admissionMapper::toAdmissionResponse);
    }

    @Override
    public AdmissionResponse updateAdmission(String admissionUuid, AdmissionUpdateRequest admissionRequest) {

        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Account id = %s doesn't exist ! ", admissionUuid)));

        admissionMapper.updateAdmissionFromRequest(admission, admissionRequest);
        admission = admissionRepository.save(admission);

        return admissionMapper.toAdmissionResponse(admission);
    }

    @Override
    public void deleteAdmission(String admissionUuid) {

        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Account = %s doesn't exist ! ", admissionUuid)));
        admissionRepository.delete(admission);
    }

    @Override
    public void disableAdmission(String uuid) {

        Admission admission = admissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ""));
        admission.setIsDeleted(true);
    }

    @Override
    public void enableAdmission(String uuid) {

        Admission admission = admissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ""));
        admission.setIsDeleted(true);

    }

    @Override
    public Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "updatedAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        Specification<Admission> specification = baseSpecification.filter(filterDto);

        Page<Admission> admissionsPage = admissionRepository.findAll(specification, pageRequest);

        return admissionsPage.map(admissionMapper::toAdmissionDetailResponse);

    }
}
