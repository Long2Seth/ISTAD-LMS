package co.istad.lms.features.student;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Authority;
import co.istad.lms.features.student.dto.*;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface StudentService {





    /**
     * Retrieves a list of students detail.z
     * @return {@link Page<StudentResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Set<Authority> getDefaultAuthoritiesStudent();







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
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<StudentResponse> getStudents(int page, int limit);





    /**
     * Retrieves a list of students.
     * @param filterDto the current page number
     * @param pageNumber is the size of record per page
     * @param pageSize is the size of record per page
     * @return {@link Page<StudentCourseResponse>} the response object containing the student details
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<StudentCourseResponse> filterStudyPrograms(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);





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
    StudentResponseDetail updateStudentByUuid (String uuid , StudentRequestUpdate studentRequest);






    /**
     * Updates an existing student.
     *
     * @param studentSettingRequest the request object containing the updated student details
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void updateSettingStudent(StudentSettingRequest studentSettingRequest);







    /**
     * Retrieves the details of a student
     *
     *
     * @return {@link StudentResponseDetail} the response object containing the student details
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StudentAchievementResponse getStudentAchievement();







    /**
     *Retrieves the details of a student
     *
     * @return {@link StudentCourseResponse} the response object containing the student
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StudentCourseResponse studentCourse();







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
     * @author Long Piseth
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
