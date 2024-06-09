package co.istad.lms.features.student;

import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestDetail;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import org.springframework.data.domain.Page;

public interface StudentService {

    /**
     * Retrieves a list of students detail.
     * @param page the current page number
     * @param limit is the size of record per page
     * @return {@link Page<StudentResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<StudentResponseDetail> getStudentsDetail(int page, int limit);



    /**
     * Retrieves a list of students.
     * @param page the current page number
     * @param limit is the size of record per page
     * @return {@link Page<StudentResponse>}
     * @since 1.0 (2024)
     */
    Page<StudentResponse> getStudents(int page, int limit);


    /**
     * Creates a new student.
     *
     * @param studentRequest is the request object containing student details for create student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void createStudent(StudentRequest studentRequest);


    /**
     * Updates an existing student.
     *
     * @param uuid    is the unique identifier of student
     * @param studentRequest the request object containing the updated student details
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StudentResponseDetail updateStudentByUuid (String uuid , StudentRequestDetail studentRequest);



    /**
     * Deletes an existing student.
     *
     * @param uuid    is the unique identifier of student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteStudentByUuid(String uuid);


    /**
     * Retrieves the details of a student by its UUID.
     *
     * @param uuid is the unique identifier of student
     * @return {@link StudentResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StudentResponseDetail getStudentDetailByUuid(String uuid);



    /**
     * Retrieves the details of a student by its UUID.
     *
     * @param uuid is the unique identifier of student
     * @return {@link StudentResponse}
     * @since 1.0 (2024)
     */
    StudentResponse getStudentByUuid(String uuid);



    /**
     * Retrieves the details of a student by its UUID.
     *
     * @param uuid is the unique identifier of student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void disableStudentByUuid(String uuid);


    /**
     * Retrieves the details of a student by its UUID.
     *
     * @param uuid is the unique identifier of student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void enableStudentByUuid(String uuid);



    /**
     * Retrieves the details of a student by its UUID.
     *
     * @param uuid is the unique identifier of student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void blockStudentByUuid(String uuid);



}
