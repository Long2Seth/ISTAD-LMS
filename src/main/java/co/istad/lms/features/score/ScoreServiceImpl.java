package co.istad.lms.features.score;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.generation.GenerationRepository;
import co.istad.lms.features.score.dto.*;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
import co.istad.lms.mapper.ScoreMapper;
import co.istad.lms.util.AssesmentsUtil;
import lombok.RequiredArgsConstructor;
import org.quartz.SimpleTrigger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScorerRepository scorerRepository;

    private final ScoreMapper scoreMapper;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final BaseSpecification<Score> baseSpecification;

    private final YearOfStudyRepository yearOfStudyRepository;

    private final StudyProgramRepository studyProgramRepository;

    private final GenerationRepository generationRepository;

    @Override
    public void createScore(ScoreRequest scoreRequest) {

        Student student =
                studentRepository.findStudentByUserUuid(scoreRequest.studentUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student = %s has not been found", scoreRequest.studentUuid())));

        Course course =
                courseRepository.findByUuid(scoreRequest.courseUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found", scoreRequest.courseUuid())));
        //map from DTO to entity
        Score score = scoreMapper.fromScoreRequest(scoreRequest);

        //validate duplicate score for a student by course
        if (scorerRepository.existsByStudentAndCourse(student, course)) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("score with student = %s and course  " +
                    "= %s has already existed", student.getUser().getUuid(), course.getUuid()));
        }

        //get total score
        Double total =
                (score.getActivityScore() * 0.1) + (score.getAttendanceScore() * 0.1) + (score.getMidtermExamScore() * 0.2) + (score.getFinalExamScore() * 0.35) + (score.getMiniProjectScore() * 0.15) + (score.getAssignmentScore() * 0.10);

        //get gpa
        Double gpa = AssesmentsUtil.getGpa(total);

        //get grade base on average
        String grade = AssesmentsUtil.getGrade(total);

        //set total to score
        score.setTotal(total);

        //set grade to score
        score.setGrade(grade);

        //set gpa to score
        score.setGpa(gpa);

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

        //get class code
        String classCode = score.getCourse().getOneClass().getClassCode();

        //map and return to DTO
        return scoreMapper.toScoreDetailResponse(score, classCode);
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
        return scores.map(score -> {
            //get class code
            String classCode = score.getCourse().getOneClass().getClassCode();

            //map and return to DTO
            return scoreMapper.toScoreDetailResponse(score, classCode);
        });
    }

    @Override
    public ScoreDetailResponse updateScoreByUuid(String uuid, ScoreUpdateRequest scoreUpdateRequest) {

        //map from DTO to entity
        Score score = scorerRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));
        //map from DTO to entity
        scoreMapper.updateScoreFromRequest(score, scoreUpdateRequest);

        //save to database
        scorerRepository.save(score);

        //get class Code
        String classCode = score.getCourse().getOneClass().getClassCode();

        //map and return to DTO
        return scoreMapper.toScoreDetailResponse(score, classCode);
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

        //map entity to DTO and return
        return scores.map(score -> {
            //get class code
            String classCode = score.getCourse().getOneClass().getClassCode();

            //map and return to DTO
            return scoreMapper.toScoreDetailResponse(score, classCode);
        });

    }

    @Override
    public Page<ScoreSemesterResponse> getAllScoresBySemester(ScoreSemesterRequest scoreSemesterRequest, int pageNumber, int pageSize) {

        StudyProgram studyProgram =
                studyProgramRepository.findByAlias(scoreSemesterRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(
                        "studyProgram = %s has not been found", scoreSemesterRequest.studyProgramAlias())));

        YearOfStudy yearOfStudy = yearOfStudyRepository.findByYearAndSemesterAndStudyProgram(scoreSemesterRequest.year(),
                scoreSemesterRequest.semester(), studyProgram).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("year of study with year = %d, semester = %d , studyProgram = %s has not been found", scoreSemesterRequest.year(), scoreSemesterRequest.semester(), studyProgram.getAlias())));

        Generation generation=
                generationRepository.findByAlias(scoreSemesterRequest.generationAlias()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("generation = %s has not been found",scoreSemesterRequest.generationAlias())));

        Set<Student> students=studentRepository.findAllByCoursesYearOfStudy(yearOfStudy);


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "lectureDate");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all lecture in database
        Page<Course> courses = courseRepository.findByOneClassGenerationAndYearOfStudy(generation,yearOfStudy,pageRequest);

        return courses.map(course->{

            Set<Score> scores=course.getScores();

            Double total =course.getScores().stream()
                    .mapToDouble(score -> score.getTotal())
                    .sum();

            scores.stream().map(score -> {
                return scoreMapper.toScoreSemesterResponse(score);
            });
            return null;

        });
    }
}
