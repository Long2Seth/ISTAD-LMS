package co.istad.lms.features.student;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestDetail;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import org.springframework.data.domain.Page;

public interface StudentService {

    Page<StudentResponse> getStudents(int page, int limit);

    StudentResponse createStudent(StudentRequest studentRequest);

    StudentResponseDetail updateStudentByUuid (String uuid , StudentRequestDetail studentRequest);

    void deleteStudentByUuid(String uuid);

    StudentResponse getStudentByUuid(String uuid);

    StudentResponse disableStudentByUuid(String uuid);

    StudentResponse enableStudentByUuid(String uuid);

    StudentResponse blockStudentByUuid(String uuid);





}
