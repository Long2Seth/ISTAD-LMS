package co.istad.lms.features.score;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Score;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.score.dto.ScoreDetailResponse;
import co.istad.lms.features.score.dto.ScoreRequest;
import co.istad.lms.features.score.dto.ScoreUpdateRequest;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.mapper.ScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScorerRepository scorerRepository;

    private final ScoreMapper scoreMapper;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final BaseSpecification<Score> baseSpecification;

    @Override
    public void createScore(ScoreRequest scoreRequest) {

        Student student =
                studentRepository.findByUuid(scoreRequest.studentUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student = %s has not been found", scoreRequest.studentUuid())));

        Course course =
                courseRepository.findByUuid(scoreRequest.courseUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found", scoreRequest.courseUuid())));
        //map from DTO to entity
        Score score = scoreMapper.fromScoreRequest(scoreRequest);

        //validate duplicate score for a student by course
        if(scorerRepository.existsByStudentAndCourse(student,course)){

            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("score with student = %s and course  " +
                    "= %s has already existed",student.getUser().getUuid(),course.getUuid()));
        }

        //set student to score
        score.setStudent(student);

        //set course to score
        score.setCourse(course);

        //set isDelete to false
        score.setIsDeleted(false);

        //random uuid and set to score
        score.setUuid(UUID.randomUUID().toString());

        //save to database
        scorerRepository.save(score);

    }

    @Override
    public ScoreDetailResponse getScoreByUuid(String uuid) {

        //find score by uuid
        Score score =
                scorerRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));

        //map and return to DTO
        return scoreMapper.toScoreDetailResponse(score);
    }

    @Override
    public Page<ScoreDetailResponse> getAllScores(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all score in database
        Page<Score> scores = scorerRepository.findAll(pageRequest);

        //map entity to DTO and return
        return scores.map(scoreMapper::toScoreDetailResponse);
    }

    @Override
    public ScoreDetailResponse updateScoreByUuid(String uuid, ScoreUpdateRequest scoreUpdateRequest) {

        //map from DTO to entity
        Score score = scorerRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));
        //map from DTO to entity
        scoreMapper.updateScoreFromRequest(score, scoreUpdateRequest);

        //save to database
        scorerRepository.save(score);

        //map and return to DTO
        return scoreMapper.toScoreDetailResponse(score);
    }

    @Override
    public void deleteScoreByUuid(String uuid) {

        //find score in database by uuid
        Score score = scorerRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));

        //delete from database
        scorerRepository.delete(score);
    }

    @Override
    public Page<ScoreDetailResponse> filterScores(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Degree entities based on the criteria provided
        Specification<Score> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Score> scores = scorerRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return scores.map(scoreMapper::toScoreDetailResponse);

    }
}
