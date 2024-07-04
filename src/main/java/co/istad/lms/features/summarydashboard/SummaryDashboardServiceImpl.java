package co.istad.lms.features.summarydashboard;

import co.istad.lms.features.classes.ClassRepository;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.lecture.LectureRepository;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.summarydashboard.dto.SummaryDashboardDetailResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryDashboardServiceImpl implements SummaryDashboardService {

    private final StudentRepository studentRepository;

    private final ClassRepository classRepository;

    private  final CourseRepository courseRepository;

    private final LectureRepository lectureRepository;

    private final LectureMapper lectureMapper;

    private final UserRepository userRepository;

    @Override
    public SummaryDashboardDetailResponse getSummaryDashboard() {
//        Long totalStudent,
//        Long totalDropStudent,
//        Long totalActiveStudent,
//        Long totalClassActive,
//        Long totalCourseActive,
//        Set<LectureResponse> currentLecture,
//
//        Long newUser,
//
//        Double totalStudentPayment
//        Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

        Long totalStudent = studentRepository.count();

        Long totalActiveStudent = studentRepository.countAllByStudentStatus(1);

        Long totalDropStudent = studentRepository.countAllByStudentStatus(3);

        Long totalClassActive = classRepository.countAllByStatus(1);

        Long totalCourseActive = courseRepository.countAllByStatus(1);

        Set<LectureResponse> currentLecture =
                lectureRepository.findAllByStatus(1).stream().map(lectureMapper::toLectureResponse).collect(Collectors.toSet());

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        Timestamp startOfMonthTimestamp = Timestamp.valueOf(startOfMonth);

        Long newUser= userRepository.countAllByCreatedAtIsAfter(startOfMonthTimestamp);

        Double totalStudentPayment = null;


        return new SummaryDashboardDetailResponse(
                totalStudent,
                totalDropStudent,
                totalActiveStudent,
                totalClassActive,
                totalCourseActive,
                currentLecture,
                newUser,
                totalStudentPayment
        );
    }
}
