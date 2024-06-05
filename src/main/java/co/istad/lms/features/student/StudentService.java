package co.istad.lms.features.student;

import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestDetail;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import org.springframework.data.domain.Page;

public interface StudentService {

    /**
     *
     * @param page the current page number
     * @param limit is the size of record per page
     * @return {@link Page<StudentResponse>}
     */
    Page<StudentResponseDetail> getStudents(int page, int limit);

    StudentResponseDetail createStudent(StudentRequest studentRequest);

    StudentResponseDetail updateStudentByUuid (String uuid , StudentRequestDetail studentRequest);

    void deleteStudentByUuid(String uuid);

    StudentResponseDetail getStudentByUuid(String uuid);

    StudentResponseDetail disableStudentByUuid(String uuid);

    StudentResponseDetail enableStudentByUuid(String uuid);

    StudentResponseDetail blockStudentByUuid(String uuid);





}
