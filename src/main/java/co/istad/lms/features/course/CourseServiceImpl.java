package co.istad.lms.features.course;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.classes.ClassRepository;
import co.istad.lms.features.course.dto.CourseDetailResponse;
import co.istad.lms.features.course.dto.CourseRequest;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseUpdateRequest;
import co.istad.lms.features.instructor.InstructorRepository;
import co.istad.lms.features.subject.SubjectRepository;
import co.istad.lms.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final SubjectRepository subjectRepository;

    private final InstructorRepository instructorRepository;

    private final ClassRepository classRepository;
    @Override
    public void createCourse(CourseRequest courseRequest) {

    }

    @Override
    public CourseDetailResponse getCourseByAlias(String alias) {
        return null;
    }

    @Override
    public Page<CourseDetailResponse> getAllCourses(int page, int size) {
        return null;
    }

    @Override
    public CourseResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest) {
        return null;
    }

    @Override
    public void deleteCourseByAlias(String alias) {

    }

    @Override
    public void enableCourseByAlias(String alias) {

    }

    @Override
    public void disableCourseByAlias(String alias) {

    }

    @Override
    public Page<CourseDetailResponse> filterCourses(BaseSpecification.FilterDto filterDto, int page, int size) {
        return null;
    }
}
