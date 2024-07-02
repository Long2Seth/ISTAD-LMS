package co.istad.lms.features.score;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.generation.GenerationRepository;
import co.istad.lms.features.score.dto.*;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.student.dto.StudentSemesterScoreResponse;
import co.istad.lms.features.student.dto.StudentTranscriptResponse;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
import co.istad.lms.mapper.CourseMapper;
import co.istad.lms.mapper.ScoreMapper;
import co.istad.lms.mapper.StudentMapper;
import co.istad.lms.util.AssesmentsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScorerRepository scoreRepository;

    private final ScoreMapper scoreMapper;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final BaseSpecification<Score> baseSpecification;

    private final YearOfStudyRepository yearOfStudyRepository;

    private final StudyProgramRepository studyProgramRepository;

    private final GenerationRepository generationRepository;

    private final CourseMapper courseMapper;

    private final StudentMapper studentMapper;

    @Value("${assessment-percentage.activity-score}")
    private Double activityScorePercentage;

    @Value("${assessment-percentage.attendance-score}")
    private Double attendanceScorePercentage;

    @Value("${assessment-percentage.final-score}")
    private Double finalScorePercentage;

    @Value("${assessment-percentage.mini-project-midterm-score}")
    private Double miniProjectScorePercentage;

    @Value("${assessment-percentage.assignment-score}")
    private Double assignmentScorePercentage;

    @Override
    public void createScore(ScoreRequest scoreRequest) {

        Student student =
                studentRepository.findStudentByUserUuid(scoreRequest.studentUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student = %s has not been found", scoreRequest.studentUuid())));

        Course course =
                courseRepository.findByUuid(scoreRequest.courseUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found", scoreRequest.courseUuid())));
        //map from DTO to entity
        Score score = scoreMapper.fromScoreRequest(scoreRequest);

        //validate duplicate score for a student by course
        if (scoreRepository.existsByStudentAndCourse(student, course)) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("score with student = %s and course  " +
                    "= %s has already existed", student.getUser().getUuid(), course.getUuid()));
        }

        //get total score
        Double total =
                (score.getActivityScore() * activityScorePercentage) +
                        (score.getAttendanceScore() * attendanceScorePercentage) +
                        (score.getFinalExamScore() * finalScorePercentage) +
                        ((score.getMiniProjectScore() + score.getMidtermExamScore()) * miniProjectScorePercentage) +
                        (score.getAssignmentScore() * assignmentScorePercentage);

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
        scoreRepository.save(score);

    }

    @Override
    public ScoreDetailResponse getScoreByUuid(String uuid) {

        //find score by uuid
        Score score =
                scoreRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));

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
        Page<Score> scores = scoreRepository.findAll(pageRequest);

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
        Score score = scoreRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));
        //map from DTO to entity
        scoreMapper.updateScoreFromRequest(score, scoreUpdateRequest);

        //save to database
        scoreRepository.save(score);

        //get class Code
        String classCode = score.getCourse().getOneClass().getClassCode();

        //map and return to DTO
        return scoreMapper.toScoreDetailResponse(score, classCode);
    }

    @Override
    public void deleteScoreByUuid(String uuid) {

        //find score in database by uuid
        Score score = scoreRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Score = %s has not been found", uuid)));

        //delete from database
        scoreRepository.delete(score);
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
        Page<Score> scores = scoreRepository.findAll(specification, pageRequest);

        //map entity to DTO and return
        return scores.map(score -> {
            //get class code
            String classCode = score.getCourse().getOneClass().getClassCode();

            //map and return to DTO
            return scoreMapper.toScoreDetailResponse(score, classCode);
        });

    }

    @Override
    public Page<StudentSemesterScoreResponse> getAllScoresBySemester(ScoreSemesterRequest scoreSemesterRequest, int pageNumber,
                                                                     int pageSize) {

        StudyProgram studyProgram =
                studyProgramRepository.findByAlias(scoreSemesterRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(
                        "studyProgram = %s has not been found", scoreSemesterRequest.studyProgramAlias())));

        YearOfStudy yearOfStudy = yearOfStudyRepository.findByYearAndSemesterAndStudyProgram(scoreSemesterRequest.year(),
                scoreSemesterRequest.semester(), studyProgram).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("year of study with year = %d, semester = %d , studyProgram = %s has not been found", scoreSemesterRequest.year(), scoreSemesterRequest.semester(), studyProgram.getAlias())));

        Generation generation =
                generationRepository.findByAlias(scoreSemesterRequest.generationAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("generation = %s has not been found", scoreSemesterRequest.generationAlias())));


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "cardId");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Student> students = studentRepository.findAllByCoursesYearOfStudy(yearOfStudy, pageRequest);

        // Map students to StudentSemesterScoreResponse and calculate sum of totals
        return students.map(student -> {
            Set<Course> coursesSet = courseRepository.findAllByOneClassGenerationAndStudentsAndYearOfStudy(generation,
                    student, yearOfStudy);

            //total score per semester
            AtomicReference<Double> total = new AtomicReference<>(0.0);
            AtomicReference<Integer> numberOfCourse = new AtomicReference<>(0);
            AtomicReference<String> classCode= new AtomicReference<>("N/A");

            // Map courses to CourseResponse and set score from Score entity
            Set<CourseSemesterScoreResponse> courses = coursesSet.stream().map(course -> {

                classCode.set(course.getOneClass().getClassCode());

                Score scoreObject = scoreRepository.findByCourseAndStudent(course, student).orElse(null);

                Double score;
                if (scoreObject != null) {
                    score = scoreObject.getTotal();
                } else {
                    score = 0.0;
                }
                total.updateAndGet(v -> v + score);
                numberOfCourse.updateAndGet(n -> n + 1);

                return new CourseSemesterScoreResponse(course.getTitle(), score);

            }).collect(Collectors.toSet());

            Double average = total.get() / numberOfCourse.get();

            String grade = AssesmentsUtil.getGrade(average);

            return studentMapper.toStudentSemesterScoreResponse(student, courses, grade, total.get(),classCode.get());
        });

    }

    @Override
    public Page<StudentTranscriptResponse> getAllTranscript(ScoreTranscriptRequest scoreTranscriptRequest, int pageNumber,
                                                            int pageSize) {

        StudyProgram studyProgram =
                studyProgramRepository.findByAlias(scoreTranscriptRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(
                        "studyProgram = %s has not been found", scoreTranscriptRequest.studyProgramAlias())));

        YearOfStudy yearOfStudy1 =
                yearOfStudyRepository.findByYearAndSemesterAndStudyProgram(scoreTranscriptRequest.year(),
                        1, studyProgram).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(
                        "year of study with year = %d, semester = %d , studyProgram = %s has not been found",
                        scoreTranscriptRequest.year(), 1, studyProgram.getAlias())));
        YearOfStudy yearOfStudy2 =
                yearOfStudyRepository.findByYearAndSemesterAndStudyProgram(scoreTranscriptRequest.year(),
                        2, studyProgram).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(
                                "year of study with year = %d, semester = %d , studyProgram = %s has not been found",
                                scoreTranscriptRequest.year(), 2, studyProgram.getAlias())));

        Generation generation =
                generationRepository.findByAlias(scoreTranscriptRequest.generationAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("generation = %s has not been found", scoreTranscriptRequest.generationAlias())));


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "cardId");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Student> students = studentRepository.findAllByCoursesYearOfStudy(yearOfStudy1, pageRequest);

        // Map students to StudentSemesterScoreResponse and calculate sum of totals
        return students.map(student -> {
            Set<Course> coursesSet1 = courseRepository.findAllByOneClassGenerationAndStudentsAndYearOfStudy(generation,
                    student, yearOfStudy1);
            Set<Course> coursesSet2 = courseRepository.findAllByOneClassGenerationAndStudentsAndYearOfStudy(generation,
                    student, yearOfStudy2);

            //total score and number of score in semester 1
            AtomicReference<Double> totalSemester1 = new AtomicReference<>(0.0);
            AtomicReference<Integer> numberOfCourseSemester1 = new AtomicReference<>(0);

            //total score and number of score in semester 2
            AtomicReference<Double> totalSemester2 = new AtomicReference<>(0.0);
            AtomicReference<Integer> numberOfCourseSemester2 = new AtomicReference<>(0);

            // Map courses to CourseResponse and set score from Score entity
            Set<CourseSemesterScoreResponse> courses1 = coursesSet1.stream().map(course -> {
                Score scoreObject = scoreRepository.findByCourseAndStudent(course, student).orElse(null);

                Double score;
                if (scoreObject != null) {
                    score = scoreObject.getTotal();
                } else {
                    score = 0.0;
                }
                totalSemester1.updateAndGet(v -> v + score);
                numberOfCourseSemester1.updateAndGet(n -> n + 1);

                return new CourseSemesterScoreResponse(course.getTitle(), score);

            }).collect(Collectors.toSet());

            // Map courses to CourseResponse and set score from Score entity
            Set<CourseSemesterScoreResponse> courses2 = coursesSet2.stream().map(course -> {
                Score scoreObject = scoreRepository.findByCourseAndStudent(course, student).orElse(null);

                Double score;
                if (scoreObject != null) {
                    score = scoreObject.getTotal();
                } else {
                    score = 0.0;
                }
//                System.out.println("semester 2 = " + score);
                totalSemester2.updateAndGet(v -> v + score);
                numberOfCourseSemester2.updateAndGet(n -> n + 1);

                return new CourseSemesterScoreResponse(course.getTitle(), score);

            }).collect(Collectors.toSet());

            Double averageSemester1 = totalSemester1.get() / ((numberOfCourseSemester1.get())>0.0?
                    numberOfCourseSemester1.get():1);


            Double averageSemester2 = totalSemester2.get() /((numberOfCourseSemester2.get())>0.0?
                    numberOfCourseSemester2.get():1);
            Double total = averageSemester1 + averageSemester2;


            Integer numberOfCourse =(numberOfCourseSemester1.get()+numberOfCourseSemester2.get())>0.0?
                    (numberOfCourseSemester1.get()+numberOfCourseSemester2.get()):1;

            Double average = total / numberOfCourse;

            String grade = AssesmentsUtil.getGrade((average));

            Double gpa = AssesmentsUtil.getGpa(average);

            return studentMapper.toStudentTranscriptResponse(student, scoreTranscriptRequest.year(), averageSemester1, averageSemester2, grade, gpa, average);
        });

    }
}
