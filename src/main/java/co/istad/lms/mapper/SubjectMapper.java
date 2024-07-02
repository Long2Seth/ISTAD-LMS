package co.istad.lms.mapper;

import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.features.media.MediaService;
import co.istad.lms.features.subject.dto.*;
import co.istad.lms.features.yearofstudy.dto.YearOfStudySubjectResponse;
import co.istad.lms.util.MediaUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.istad.lms.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "curriculum", source = "curriculum", qualifiedByName = "jsonNodeToString")
    Subject fromDegreeRequest(SubjectRequest degreeCreateRequest);

    @Mapping(target = "curriculum", source = "curriculum", qualifiedByName = "stringToJsonNode")
    @Mapping(source = "subject.logo", target = "logo", qualifiedByName = "getLogoUrl")
    SubjectDetailResponse toSubjectDetailResponse(Subject subject);

//    @Mapping(target = "curriculum", source = "subject.curriculum", qualifiedByName = "stringToJsonNode")
    @Mapping(target = "yearOfStudy", qualifiedByName = "toYearOfStudy")
    @Mapping(source = "subject.isDeleted", target = "isDeleted")
    @Mapping(source = "subject.isDraft", target = "isDraft")
    @Mapping(source = "subject.logo", target = "logo", qualifiedByName = "getLogoUrl")
    SubjectYearOfStudyDetailResponse toSubjectYearOfStudyDetailResponse(Subject subject, YearOfStudySubjectResponse yearOfStudy);

    @Named("toYearOfStudy")
    default YearOfStudySubjectResponse toYearOfStudy(YearOfStudySubjectResponse yearOfStudy) {
        return yearOfStudy;
    }

    @Named("getLogoUrl")
    default String getLogoUrl(String logo) {

        if (logo != null && !logo.trim().isEmpty()) {
            return MediaUtil.getUrl(logo);
        } else {
            return null;
        }
    }

    SubjectResponse toSubjectResponse(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "curriculum", source = "curriculum",qualifiedByName = "stringToJsonNode")
    void updateSubjectFromRequest(@MappingTarget Subject subject, SubjectUpdateRequest subjectUpdateRequest);

    @Named("jsonNodeToString")
    default String jsonNodeToString(JsonNode jsonNode) {
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Named("stringToJsonNode")
    default JsonNode stringToJsonNode(String curriculum) {
        try {
            return objectMapper.readTree(curriculum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
