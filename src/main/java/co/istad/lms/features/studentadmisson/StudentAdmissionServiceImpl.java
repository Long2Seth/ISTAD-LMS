package co.istad.lms.features.studentadmisson;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.features.admission.AdmissionRepository;
import co.istad.lms.features.admission.AdmissionService;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.degree.DegreeRepository;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionRequest;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionUpdateRequest;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.telegrambot.TelegramBotService;
import co.istad.lms.mapper.StudentAdmissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentAdmissionServiceImpl implements StudentAdmissionService {

    private final StudentAdmissionRepository studentAdmissionRepository;

    private final StudentAdmissionMapper studentAdmissionMapper;

    private final DegreeRepository degreeRepository;

    private final ShiftRepository shiftRepository;

    private final StudyProgramRepository studyProgramRepository;

    private final TelegramBotService telegramBotService;

    private final BaseSpecification<StudentAdmission> baseSpecification;

    private final AdmissionRepository admissionRepository;


    @Override
    public void createStudentAdmission(StudentAdmissionRequest studentAdmissionRequest) {

        //find admission that open
        Admission admission = admissionRepository.findByStatus(1).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "admission has been closed"));

        //validate degree by degree alias
        Degree degree = degreeRepository.findByAlias(studentAdmissionRequest.degreeAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("degree = %s has not been found", studentAdmissionRequest.degreeAlias())));

        //validate shift by shift alias
        Shift shift = shiftRepository.findByAlias(studentAdmissionRequest.shiftAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("shift = %s has not been found", studentAdmissionRequest.shiftAlias())));

        //validate
        StudyProgram studyProgram = studyProgramRepository.findByAlias(studentAdmissionRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("studyProgram = %s has not been found", studentAdmissionRequest.studyProgramAlias())));

        //map from DTO to entity
        StudentAdmission studentAdmission = studentAdmissionMapper.fromStudentAdmissionRequest(studentAdmissionRequest);

        //generate uuid for admission
        studentAdmission.setUuid(UUID.randomUUID().toString());

        //set shift to entity
        studentAdmission.setShift(shift);

        //set degree to entity
        studentAdmission.setDegree(degree);

        // studyProgram to entity
        studentAdmission.setStudyProgram(studyProgram);

        //set admission to student admission
        studentAdmission.setAdmission(admission);

        //save to database
        studentAdmissionRepository.save(studentAdmission);

//         Send a notification to Telegram
        telegramBotService.sendAdmissionResponse(studentAdmission);
    }

    @Override
    public StudentAdmissionDetailResponse getStudentAdmissionByUuid(String uuid) {

        //find student admission in database by uuid
        StudentAdmission admission = studentAdmissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("StudentAdmission = %s has not been found ", uuid)));

        //save to database and return AdmissionDetail
        return studentAdmissionMapper.toStudentAdmissionDetailResponse(admission);
    }

    @Override
    public Page<StudentAdmissionDetailResponse> getAllStudentAdmissions(int pageNumber, int pageSize) {

        //create sort order
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        //find all student admission in database
        Page<StudentAdmission> admissionsPage = studentAdmissionRepository.findAll(pageRequest);

        //map entity to database and return student AdmissionDetail
        return admissionsPage.map(studentAdmissionMapper::toStudentAdmissionDetailResponse);
    }

    @Override
    public StudentAdmissionDetailResponse updateStudentAdmission(String uuid, StudentAdmissionUpdateRequest studentAdmissionUpdateRequest) {

        //find student admission by uuid in database
        StudentAdmission studentAdmission = studentAdmissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student Admission  = %s has not been found ! ", uuid)));

        //map data from DTO to entity
        studentAdmissionMapper.updateStudentAdmissionFromRequest(studentAdmission, studentAdmissionUpdateRequest);

        //validate degree from dto with entity
        //if the same, don't update
        if (studentAdmissionUpdateRequest.degreeAlias() != null && !studentAdmissionUpdateRequest.degreeAlias().equals(studentAdmission.getDegree().getAlias())) {

            //find degree in database with uuid
            Degree degree = degreeRepository.findByAlias(studentAdmissionUpdateRequest.degreeAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("degree = %s has not been found", studentAdmissionUpdateRequest.degreeAlias())));

            //set new degree
            studentAdmission.setDegree(degree);
        }

        //validate shift from dto with entity
        //if the same, don't update
        if (studentAdmissionUpdateRequest.shiftAlias() != null && !studentAdmissionUpdateRequest.shiftAlias().equals(studentAdmission.getShift().getAlias())) {

            //find shift in database with uuid
            Shift shift = shiftRepository.findByAlias(studentAdmissionUpdateRequest.shiftAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("shift = %s has not been found", studentAdmissionUpdateRequest.shiftAlias())));

            //set new shift
            studentAdmission.setShift(shift);
        }

        //validate studyProgram from dto with entity
        //if the same, don't update
        if (studentAdmissionUpdateRequest.studyProgramAlias() != null && !studentAdmissionUpdateRequest.studyProgramAlias().equals(studentAdmission.getStudyProgram().getAlias())) {

            //find studyProgram in database by uuid
            StudyProgram studyProgram = studyProgramRepository.findByAlias(studentAdmissionUpdateRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("studyProgram = %s has not been found", studentAdmissionUpdateRequest.studyProgramAlias())));

            //set new studyProgram
            studentAdmission.setStudyProgram(studyProgram);
        }

        //save to database
        studentAdmissionRepository.save(studentAdmission);

        //return admissionResponse to controller
        return studentAdmissionMapper.toStudentAdmissionDetailResponse(studentAdmission);
    }

    @Override
    public void deleteStudentAdmission(String uuid) {

        //validate student admission by uuid
        StudentAdmission studentAdmission = studentAdmissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", uuid)));

        //delete from database
        studentAdmissionRepository.delete(studentAdmission);
    }

    @Override
    public void disableStudentAdmissionByUuid(String uuid) {

        //validate from dto with uuid
        StudentAdmission studentAdmission = studentAdmissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", uuid)));

        //set isDeleted to true (disable)
        studentAdmission.setIsDeleted(true);

        //save to database
        studentAdmissionRepository.save(studentAdmission);
    }

    @Override
    public void enableStudentAdmissionByUuid(String uuid) {

        //validate from dto by uuid
        StudentAdmission studentAdmission = studentAdmissionRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Admission = %s has not been found ! ", uuid)));

        //set isDeleted to false(enable)
        studentAdmission.setIsDeleted(false);

        //save to database
        studentAdmissionRepository.save(studentAdmission);
    }

    @Override
    public Page<StudentAdmissionDetailResponse> filterStudentAdmissions(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Admission entities based on the criteria provided
        Specification<StudentAdmission> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<StudentAdmission> admissionsPage = studentAdmissionRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return admissionsPage.map(studentAdmissionMapper::toStudentAdmissionDetailResponse);

    }
}
