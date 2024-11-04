package co.istad.lms.features.course;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Class;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Subject;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.classes.ClassRepository;
import co.istad.lms.features.course.dto.CourseDetailResponse;
import co.istad.lms.features.course.dto.CourseRequest;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseUpdateRequest;
import co.istad.lms.features.instructor.InstructorRepository;
import co.istad.lms.features.media.MediaService;
import co.istad.lms.features.subject.SubjectRepository;
import co.istad.lms.mapper.CourseMapper;
import co.istad.lms.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final SubjectRepository subjectRepository;

    private final InstructorRepository instructorRepository;

    private final ClassRepository classRepository;

    private final BaseSpecification<Course> baseSpecification;

    private final MediaService mediaService;

    @Override
    public void createCourse(CourseRequest courseRequest) {

        //validate class from DTO by class uuid
        Class aClass =
                classRepository.findByUuid(courseRequest.classUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Class = %s has not been found", courseRequest.classUuid())));

        LocalDate courseStart=null;
        //validate courseStart null or not
        if(courseRequest.courseStart()!=null&&!courseRequest.courseStart().trim().isEmpty()){

            //validate format for course start
            courseStart= DateTimeUtil.stringToLocalDate(courseRequest.courseStart(),"courseStart");
        }

        //validate subject from DTO by subject alias
        Subject subject=
                subjectRepository.findByAlias(courseRequest.subjectAlias()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Subject = %s has not been found",courseRequest.subjectAlias())));

        //map from DTO to entity
        Course course = courseMapper.fromCourseRequest(courseRequest);

        //validate instructor from DTO by instructor uuid
        if(courseRequest.instructorUuid()!=null){

            Instructor instructor=
                    instructorRepository.findInstructorByUserUuid(courseRequest.instructorUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found", courseRequest.instructorUuid())));

            //set instructor to course
            course.setInstructor(instructor);
        }
        //set class to course
        course.setOneClass(aClass);

        //set courseStart to curse
        course.setCourseStart(courseStart);

        //set subject to course
        course.setSubject(subject);

        //set to enable
        course.setIsDeleted(false);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public CourseDetailResponse getCourseByUuid(String uuid) {

        //validate course from DTO
        Course course =
                courseRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found", uuid)));

        if (course.getSubject().getLogo() != null && !course.getSubject().getLogo().trim().isEmpty()) {
            Subject subject=course.getSubject();
            subject.setLogo(mediaService.getUrl(subject.getLogo()));
            course.setSubject(subject);
        }
        //map to dto and return
        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public Page<CourseDetailResponse> getAllCourses(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all courses in database
        Page<Course> courses = courseRepository.findAll(pageRequest);

        //get unique subject
        Map<Long, Subject> uniqueSubjects = courses.stream()
                .map(Course::getSubject)
                .distinct()
                .peek(subject -> {
                    if (subject.getLogo() != null && !subject.getLogo().trim().isEmpty()) {
                        subject.setLogo(mediaService.getUrl(subject.getLogo()));
                    }
                })
                .collect(Collectors.toMap(Subject::getId, Function.identity()));

        // Set updated subjects back to courses
        courses.forEach(course -> {
            Subject updatedSubject = uniqueSubjects.get(course.getSubject().getId());
            course.setSubject(updatedSubject);
        });


        //map entity to DTO and return
        return courses.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public CourseDetailResponse updateCourseByUuid(String uuid, CourseUpdateRequest courseUpdateRequest) {

        //find degree by uuid
        Course course =
                courseRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found.", uuid)));

        //validate courseStart null or not
        if(courseUpdateRequest.courseStart()!=null&&!courseUpdateRequest.courseStart().trim().isEmpty()){

            //validate format for course start
            LocalDate courseStart= DateTimeUtil.stringToLocalDate(courseUpdateRequest.courseStart(),"courseStart");

            //set courseStart
            course.setCourseStart(courseStart);
        }

        //map DTO to entity
        courseMapper.updateCourseFromRequest(course, courseUpdateRequest);

        //save to database
        courseRepository.save(course);

        if (course.getSubject().getLogo() != null && !course.getSubject().getLogo().trim().isEmpty()) {
            Subject subject=course.getSubject();
            subject.setLogo(mediaService.getUrl(subject.getLogo()));
            course.setSubject(subject);
        }

        //return Degree DTO
        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public void deleteCourseByUuid(String uuid) {

        //find course in database by uuid
        Course course =
                courseRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found.", uuid)));

        //delete course in database
        courseRepository.delete(course);
    }

    @Override
    public void enableCourseByUuid(String uuid) {

        //validate degree from dto by uuid
        Course course =
                courseRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found ! ", uuid)));

        //set isDeleted to false(enable)
        course.setIsDeleted(false);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public void disableCourseByUuid(String uuid) {

        //validate course from dto by uuid
        Course course =
                courseRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s has not been found ! ", uuid)));

        //set isDeleted to true(disable)
        course.setIsDeleted(true);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public Page<CourseDetailResponse> filterCourses(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering course entities based on the criteria provided
        Specification<Course> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Course> courses = courseRepository.findAll(specification, pageRequest);

        //get unique subject
        Map<Long, Subject> uniqueSubjects = courses.stream()
                .map(Course::getSubject)
                .distinct()
                .peek(subject -> {
                    if (subject.getLogo() != null && !subject.getLogo().trim().isEmpty()) {
                        subject.setLogo(mediaService.getUrl(subject.getLogo()));
                    }
                })
                .collect(Collectors.toMap(Subject::getId, Function.identity()));

        // Set updated subjects back to courses
        courses.forEach(course -> {
            Subject updatedSubject = uniqueSubjects.get(course.getSubject().getId());
            course.setSubject(updatedSubject);
        });

        //map to DTO and return
        return courses.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public CourseDetailResponse addInstructorToCourse(String uuid, String instructorUuid) {

        //validate course from DTO by uuid
        Course course=
                courseRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found",uuid)));

        //validate instructor from DTO by uuid
        Instructor instructor=
                instructorRepository.findInstructorByUserUuid(instructorUuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found",instructorUuid)));

        //add instructor to course
        course.setInstructor(instructor);

        //save to database
        courseRepository.save(course);

        if(course.getSubject().getLogo()!=null&&!course.getSubject().getLogo().trim().isEmpty()){
            Subject subject=course.getSubject();
            subject.setLogo(mediaService.getUrl(subject.getLogo()));
            course.setSubject(subject);
        }

        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public void deleteInstructorFromCourse(String uuid, String instructorUuid) {
        //validate course from DTO by uuid
        Course course=
                courseRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found",uuid)));

        //validate instructor from DTO by uuid
        Instructor instructor=
                instructorRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found",uuid)));

        course.setInstructor(null);
    }
}
