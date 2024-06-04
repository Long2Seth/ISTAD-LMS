package co.istad.lms.features.instructor;

import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import org.springframework.data.domain.Page;

public interface InstructorService {

    InstructorResponse createInstructor(InstructorRequest instructorRequest);

    InstructorResponseDetail updateInstructorByUuid(String uuid, InstructorRequestDetail instructorRequestDetail);

    InstructorResponseDetail getInstructorDetailByUuid(String uuid);

    InstructorResponse getInstructorByUuid(String uuid);


    void deleteInstructorByUuid( String uuid);

    InstructorResponseDetail disableInstructorByUuid( String uuid);

    InstructorResponseDetail enableInstructorByUuid( String uuid);

    InstructorResponseDetail blockInstructorByUuid( String uuid);

    Page<InstructorResponseDetail> getAllInstructorDetail(int page, int limit);

    Page<InstructorResponse> getAllInstructor(String search, int page, int limit);


}
