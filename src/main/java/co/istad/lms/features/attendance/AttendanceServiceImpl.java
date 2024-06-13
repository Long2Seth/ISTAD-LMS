package co.istad.lms.features.attendance;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Attendance;

import co.istad.lms.domain.Lecture;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.attendance.dto.AttendanceDetailResponse;
import co.istad.lms.features.attendance.dto.AttendanceRequest;
import co.istad.lms.features.attendance.dto.AttendanceResponse;
import co.istad.lms.features.attendance.dto.AttendanceUpdateRequest;
import co.istad.lms.features.lecture.LectureRepository;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.mapper.AttendanceMapper;
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
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    private final AttendanceRepository attendanceRepository;

    private final BaseSpecification<Attendance> baseSpecification;

    private final LectureRepository lectureRepository;

    private final StudentRepository studentRepository;


    @Override
    public void createAttendance(AttendanceRequest attendanceRequest) {

        //validate lecture from DTO by alias
        Lecture lecture =
                lectureRepository.findByUuid(attendanceRequest.lectureUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Lecture = %s has not been found", attendanceRequest.lectureUuid())));

        //validate student from DTO by uuid
        Student student = studentRepository.findByUuid(attendanceRequest.studentUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student = %s has not been found", attendanceRequest.studentUuid())));

        //check duplicate attendance by student and lecture
        if (attendanceRepository.existsByStudentAndLecture(student, lecture)) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("attendance with student =n %s and " +
                    "lecture  = %s has already existed", student.getUser().getUuid(), lecture.getUuid()));
        }

        // map DTO to entity
        Attendance attendance = attendanceMapper.fromAttendanceRequest(attendanceRequest);

        //random uuid to attendance
        attendance.setUuid(UUID.randomUUID().toString());

        //set lecture to attendance
        attendance.setLecture(lecture);

        //set student to attendance
        attendance.setStudent(student);

        //save to database
        attendanceRepository.save(attendance);


    }

    @Override
    public AttendanceDetailResponse getAttendanceByUuid(String Uuid) {

        //find attendance by uuid
        Attendance attendance = attendanceRepository.findByUuid(Uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Attendance = %s has not been found.", Uuid)));

        //return attendance detail
        return attendanceMapper.toAttendanceDetailResponse(attendance);

    }

    @Override
    public Page<AttendanceDetailResponse> getAllAttendances(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all attendance in database
        Page<Attendance> attendance = attendanceRepository.findAll(pageRequest);

        //map entity to DTO and return
        return attendance.map(attendanceMapper::toAttendanceDetailResponse);

    }

    @Override
    public AttendanceResponse updateAttendanceByUuid(String uuid, AttendanceUpdateRequest attendanceUpdateRequest) {

        //find attendance by uuid
        Attendance attendance = attendanceRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Attendance = %s has not been found.", uuid)));

        //map DTO to entity
        attendanceMapper.updateAttendanceFromRequest(attendance, attendanceUpdateRequest);

        //save to database
        attendanceRepository.save(attendance);

        //return Attendance response
        return attendanceMapper.toAttendanceResponse(attendance);

    }

    @Override
    public void deleteAttendanceByUuid(String uuid) {

        //find attendance in database by uuid
        Attendance attendance = attendanceRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Attendance = %s has not been found.", uuid)));

        //delete attendance in database
        attendanceRepository.delete(attendance);

    }

    @Override
    public void enableAttendanceByUuid(String uuid) {

        //validate attendance from dto by uuid
        Attendance attendance = attendanceRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Attendance = %s has not been found ! ", uuid)));


        //save to database
        attendanceRepository.save(attendance);

    }

    @Override
    public void disableAttendanceByUuid(String uuid) {

        //validate attendance from dto by uuid
        Attendance attendance = attendanceRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Attendance = %s has not been found ! ", uuid)));


        //save to database
        attendanceRepository.save(attendance);

    }

    @Override
    public Page<AttendanceDetailResponse> filterAttendances(BaseSpecification.FilterDto filterDto, int page, int size) {


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Degree entities based on the criteria provided
        Specification<Attendance> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Attendance> attendances = attendanceRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return attendances.map(attendanceMapper::toAttendanceDetailResponse);

    }
}
