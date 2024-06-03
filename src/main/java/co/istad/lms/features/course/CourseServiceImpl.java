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
import co.istad.lms.features.subject.SubjectRepository;
import co.istad.lms.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final SubjectRepository subjectRepository;

    private final InstructorRepository instructorRepository;

    private final ClassRepository classRepository;

    private final BaseSpecification<Course> baseSpecification;

    @Override
    public void createCourse(CourseRequest courseRequest) {

        //validate course from DTO
        if (courseRepository.existsByAlias(courseRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.NO_CONTENT, String.format("Course = %s has not been found", courseRequest.alias()));
        }

        //validate class from DTO by class alias
        Class aClass =
                classRepository.findByAlias(courseRequest.classAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Class = %s has not been found", courseRequest.classAlias())));

        //validate subject from DTO by subject alias
        Subject subject=
                subjectRepository.findByAlias(courseRequest.subjectAlias()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Subject = %s has not been found",courseRequest.subjectAlias())));

        //map from DTO to entity
        Course course = courseMapper.fromCourseRequest(courseRequest);

        //validate instructor from DTO by instructor uuid
        if(courseRequest.instructorUuid()!=null){

            Instructor instructor=
                    instructorRepository.findByUuid(courseRequest.instructorUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found", courseRequest.instructorUuid())));

            //set instructor to course
            course.setInstructor(instructor);
        }
        //set class to course
        course.setAClass(aClass);

        //set subject to course
        course.setSubject(subject);

        //set to enable
        course.setIsDeleted(false);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public CourseDetailResponse getCourseByAlias(String alias) {

        //validate course from DTO
        Course course = courseRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found", alias)));

        //map to dto and return
        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public Page<CourseDetailResponse> getAllCourses(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all courses in database
        Page<Course> courses = courseRepository.findAll(pageRequest);

        //map entity to DTO and return
        return courses.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public CourseDetailResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest) {

        //find degree by alias
        Course course = courseRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found.", alias)));

        //check null alias from DTO
        if (courseUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(courseUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (courseRepository.existsByAlias(courseUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Course = %s already exist.", courseUpdateRequest.alias()));
                }
            }
        }

        //map DTO to entity
        courseMapper.updateCourseFromRequest(course, courseUpdateRequest);

        //save to database
        courseRepository.save(course);

        //return Degree DTO
        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public void deleteCourseByAlias(String alias) {

        //find course in database by alias
        Course course = courseRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found.", alias)));

        //delete course in database
        courseRepository.delete(course);
    }

    @Override
    public void enableCourseByAlias(String alias) {

        //validate degree from dto by alias
        Course course = courseRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        course.setIsDeleted(false);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public void disableCourseByAlias(String alias) {

        //validate course from dto by alias
        Course course = courseRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Degree = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        course.setIsDeleted(true);

        //save to database
        courseRepository.save(course);
    }

    @Override
    public Page<CourseDetailResponse> filterCourses(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering course entities based on the criteria provided
        Specification<Course> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Course> courses = courseRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return courses.map(courseMapper::toCourseDetailResponse);
    }

    @Override
    public CourseDetailResponse addInstructorToCourse(String alias, String uuid) {

        //validate course from DTO by alias
        Course course=
                courseRepository.findByAlias(alias).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Course = %s has not been found",alias)));

        //validate instructor from DTO by uuid
        Instructor instructor=
                instructorRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found",uuid)));

        //add instructor to course
        course.setInstructor(instructor);

        //save to database
        courseRepository.save(course);

        return courseMapper.toCourseDetailResponse(course);
    }

    @Override
    public void deleteInstructorFromCourse(String alias, String uuid) {
        //validate course from DTO by alias
        Course course=
                courseRepository.findByAlias(alias).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found",alias)));

        //validate instructor from DTO by uuid
        Instructor instructor=
                instructorRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Instructor = %s has not been found",uuid)));

        course.setInstructor(null);
    }
}
