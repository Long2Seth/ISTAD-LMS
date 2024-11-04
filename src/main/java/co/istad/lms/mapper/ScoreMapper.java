package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Score;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.score.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {StudentMapper.class, })
public interface ScoreMapper {

    Score fromScoreRequest(ScoreRequest scoreRequest);

    ScoreDetailResponse toScoreDetailResponse(Score score,String classCode);

    @Mapping(source = "score.student.user.dob",target = "student.dob")
    @Mapping(source = "score.student.user.gender",target = "student.gender")
    @Mapping(source = "score.student.user.nameEn",target = "student.nameEn")
    ScoreEachSemesterResponse toScoreEachSemesterResponse(Score score,String classCode);


    ScoreResponse toScoreResponse(Score score);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateScoreFromRequest(@MappingTarget Score score, ScoreUpdateRequest scoreUpdateRequest);

    @Mapping(source = "course",target = "courses",ignore = true)
    ScoreSemesterResponse toScoreSemesterResponse(Score score);
}
