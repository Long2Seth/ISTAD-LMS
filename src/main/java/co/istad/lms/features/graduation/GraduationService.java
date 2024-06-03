package co.istad.lms.features.graduation;

import co.istad.lms.features.graduation.dto.GraduationRequest;
import co.istad.lms.features.graduation.dto.GraduationResponse;
import org.springframework.data.domain.Page;

public interface GraduationService {

    Page<GraduationResponse> findAll(int page, int limit);

    GraduationResponse findById(String uuid);

    GraduationResponse createGraduation (GraduationRequest graduationRequest);

    GraduationResponse updateGraduation (String uuid, GraduationRequest graduationRequest);

    void deleteGraduation (String uuid);


}
