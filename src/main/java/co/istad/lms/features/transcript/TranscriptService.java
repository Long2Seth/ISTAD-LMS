package co.istad.lms.features.transcript;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Transcript;
import co.istad.lms.features.transcript.dto.TranscriptRequest;
import co.istad.lms.features.transcript.dto.TranscriptResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TranscriptService {

    void createTranscript(TranscriptRequest transcriptRequest);

    TranscriptResponse updateTranscript(TranscriptRequest transcriptRequest);

    Page<TranscriptResponse> getAllTranscript(int pageNumber, int pageSize);

    Page<TranscriptResponse> filterTranscript(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);
}
