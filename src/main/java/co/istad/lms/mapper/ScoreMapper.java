package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Score;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.score.dto.ScoreDetailResponse;
import co.istad.lms.features.score.dto.ScoreRequest;
import co.istad.lms.features.score.dto.ScoreResponse;
import co.istad.lms.features.score.dto.ScoreUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ScoreMapper {

    Score fromScoreRequest(ScoreRequest scoreRequest);

    ScoreDetailResponse toScoreDetailResponse(Score score);

    ScoreResponse toScoreResponse(Score score);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateScoreFromRequest(@MappingTarget Score score, ScoreUpdateRequest scoreUpdateRequest);

}
