package co.istad.lms.features.instructor;

import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import org.springframework.data.domain.Page;

public interface InstructorService {

    InstructorResponseDetail createInstructor(InstructorRequest instructorRequest);

    InstructorResponseDetail updateInstructorByUuid(String uuid, InstructorRequestDetail instructorRequestDetail);

    InstructorResponseDetail getInstructorByUuid(String uuid);

    void deleteInstructorByUuid( String uuid);

    InstructorResponseDetail disableInstructorByUuid( String uuid);

    InstructorResponseDetail enableInstructorByUuid( String uuid);

    InstructorResponseDetail blockInstructorByUuid( String uuid);

    Page<InstructorResponseDetail> getAllInstructor(int page, int limit);


}
