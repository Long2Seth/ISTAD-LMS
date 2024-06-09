package co.istad.lms.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.istad.lms.domain.Subject;
import co.istad.lms.features.subject.dto.SubjectDetailResponse;
import co.istad.lms.features.subject.dto.SubjectRequest;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.subject.dto.SubjectUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "curriculum", source = "curriculum",qualifiedByName = "jsonNodeToString")
    Subject fromDegreeRequest(SubjectRequest degreeCreateRequest);

    @Mapping(target = "curriculum", source = "curriculum",qualifiedByName = "stringToJsonNode")
    SubjectDetailResponse toSubjectDetailResponse(Subject subject);


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
