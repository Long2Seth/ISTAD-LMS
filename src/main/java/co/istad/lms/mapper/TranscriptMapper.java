package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Transcript;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.transcript.dto.TranscriptRequest;
import co.istad.lms.features.transcript.dto.TranscriptResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TranscriptMapper {

    Transcript fromTranscriptRequest(TranscriptRequest transcriptRequest);

    TranscriptResponse toTranscriptResponse(Transcript transcript);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTranscriptFromRequest(@MappingTarget Transcript transcript,TranscriptRequest transcriptRequest);

}
