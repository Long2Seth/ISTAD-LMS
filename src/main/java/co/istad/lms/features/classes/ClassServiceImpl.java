package co.istad.lms.features.classes;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Class;
import co.istad.lms.domain.*;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.classes.dto.ClassAddStudentRequest;
import co.istad.lms.features.classes.dto.ClassDetailResponse;
import co.istad.lms.features.classes.dto.ClassRequest;
import co.istad.lms.features.classes.dto.ClassUpdateRequest;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.generation.GenerationRepository;
import co.istad.lms.features.instructor.InstructorRepository;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.student.StudentService;
import co.istad.lms.features.studentadmisson.StudentAdmissionRepository;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
import co.istad.lms.mapper.ClassMapper;
import co.istad.lms.mapper.StudentAdmissionMapper;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    private final ClassMapper classMapper;

    private final BaseSpecification<Class> baseSpecification;

    private final StudyProgramRepository studyProgramRepository;

    private final ShiftRepository shiftRepository;

    private final GenerationRepository generationRepository;

    private final InstructorRepository instructorRepository;

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    private final YearOfStudyRepository yearOfStudyRepository;

    private final StudentAdmissionRepository studentAdmissionRepository;

    private final StudentAdmissionMapper studentAdmissionMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public void createClass(ClassRequest classRequest) {


        //map from DTO to entity
        Class aClass = classMapper.fromClassRequest(classRequest);

        //find studyProgram by studyPramAlias in classRequest
        StudyProgram studyProgram =
                studyProgramRepository.findByAliasAndIsDeletedFalse(classRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("StudyProgram = %s has not been found", classRequest.studyProgramAlias())));

        //find yearOfStudy of studyProgram and semester1
        Set<YearOfStudy> yearOfStudies = yearOfStudyRepository.findByYearAndStudyProgram(classRequest.year(),
                studyProgram);

        //check year of study available or not
        if (yearOfStudies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Year = %s has not been found",
                    classRequest.year()));
        }

        //get all subject in semester
        Set<Subject> subjects = yearOfStudies.stream()
                .flatMap(yearOfStudy -> yearOfStudy.getSubjects().stream())
                .collect(Collectors.toSet());

        Set<Course> allCourse = subjects.stream()
                .map(subject -> {
                    Course course = new Course();
                    course.setOneClass(aClass);
                    course.setSubject(subject);
                    course.setUuid(UUID.randomUUID().toString());
                    course.setTitle(subject.getTitle());
                    course.setIsDeleted(false);
                    course.setIsDraft(true);
                    course.setIsStarted(false);
                    return course;
                })
                .collect(Collectors.toSet());


//        find instructor by instructorUuid in classRequest
        if (classRequest.instructorUuid() != null) {

            Instructor instructor = instructorRepository.findByUuid(classRequest.instructorUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Instructor = %s has not been found", classRequest.instructorUuid())));

            //set instructor to class
            aClass.setInstructor(instructor);
        }

        //find shift by shiftAlias in classRequest
        Shift shift = shiftRepository.findByAlias(classRequest.shiftAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found", classRequest.shiftAlias())));

        //find generation by generationAlias in classRequest
        Generation generation = generationRepository.findByAlias(classRequest.generationAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found", classRequest.generationAlias())));

        //check all student alias from DTO null or not
        if (classRequest.studentUuid() != null) {

            //find student by studentUuid from dto
            Set<Student> students =
                    classRequest.studentUuid().stream().map(studentUui ->
                            studentRepository.findByUuid(studentUui).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s has not been found.", studentUui)))

                    ).collect(Collectors.toSet());

            aClass.setStudents(students);

            allCourse.forEach(course -> course.setStudents(students));

            //set student to class
        }

        //set shift to entity
        aClass.setShift(shift);

        //set all course to cass
        aClass.setCourses(allCourse);

        //set uuid of study to class
        aClass.setUuid(UUID.randomUUID().toString());

        //set generation to entity
        aClass.setGeneration(generation);

        //set studyProgram to entity
        aClass.setStudyProgram(studyProgram);

        //set isDeleted to false(enable)
        aClass.setIsDeleted(false);

        //save to database
        classRepository.save(aClass);

    }

    @Override
    public ClassDetailResponse getClassByUuid(String alias) {

        //find class by uuid
        Class aClass =
                classRepository.findByUuid(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found.", alias)));

        //return degree detail
        return classMapper.toClassDetailResponse(aClass);
    }

    @Override
    public Page<ClassDetailResponse> getAllClasses(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all classes in database
        Page<Class> classes = classRepository.findAll(pageRequest);

        //map entity to DTO and return
        return classes.map(classMapper::toClassDetailResponse);
    }

    @Override
    public ClassDetailResponse updateClassByUuid(String uuid, ClassUpdateRequest classUpdateRequest) {

        //validate class from DTO
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found", uuid)));

        //check studyProgram from update DTO
        if (classUpdateRequest.studyProgramAlias() != null) {

            //find studyProgram by studyPramAlias in classRequest
            StudyProgram studyProgram = studyProgramRepository.findByAlias(classUpdateRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("StudyProgram = %s has not been found", classUpdateRequest.studyProgramAlias())));

            //set studyProgram to entity
            aClass.setStudyProgram(studyProgram);

        }

        //check instructor from update DTO
        if (classUpdateRequest.instructorUuid() != null) {

            //find instructor by instructorUuid in classRequest
            Instructor instructor = instructorRepository.findByUuid(classUpdateRequest.instructorUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Instructor = %s has not been found", classUpdateRequest.instructorUuid())));

//            set instructor to entity
            aClass.setInstructor(instructor);
        }


        //check shift from update DTO
        if (classUpdateRequest.shiftAlias() != null) {

            //find shift by shiftAlias in classRequest
            Shift shift = shiftRepository.findByAlias(classUpdateRequest.shiftAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Shift = %s has not been found", classUpdateRequest.shiftAlias())));

            //set shift to entity
            aClass.setShift(shift);
        }

        //check generation from update DTO
        if (classUpdateRequest.generationAlias() != null) {

            //find generation by generationAlias in classRequest
            Generation generation = generationRepository.findByAlias(classUpdateRequest.generationAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found", classUpdateRequest.generationAlias())));

            //set generation to entity
            aClass.setGeneration(generation);

        }

        //map from DTO to entity
        classMapper.updateClassFromRequest(aClass, classUpdateRequest);

        //save to entity
        classRepository.save(aClass);

        return classMapper.toClassDetailResponse(aClass);

    }

    @Override
    public void deleteClassByUuid(String uuid) {

        //find class in database by uuid
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found.", uuid)));

        //delete class in database
        classRepository.delete(aClass);
    }

    @Override
    public void enableClassByUuid(String uuid) {

        //validate class from dto by uuid
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found ! ", uuid)));

        //set isDeleted to false(enable)
        aClass.setIsDeleted(false);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public void disableClassByUuid(String uuid) {

        //validate class from dto by alias
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found ! ", uuid)));

        //set isDeleted to true(disable)
        aClass.setIsDeleted(true);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public Page<ClassDetailResponse> filterClasses(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Class entities based on the criteria provided
        Specification<Class> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Class> classes = classRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return classes.map(classMapper::toClassDetailResponse);

    }

    @Override
    public ClassDetailResponse addStudent(String uuid, ClassAddStudentRequest classAddStudentRequest) {

        //validate class from DTO by uuid
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found", uuid)));

        //find all studentAdmission from DTO in database to add by student uuid
        Set<StudentAdmission> studentAdmissions =
                classAddStudentRequest.studentAdmissionUuid().stream().peek(studentAdmissionUuid -> {
                    if (studentAdmissionUuid.length() > 100) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("StudentAdmission UUID = %s exceeds the maximum length of 100 characters", studentAdmissionUuid));
                    }
                }).map(studentAdmissionUuid ->
                        studentAdmissionRepository.findByUuid(studentAdmissionUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("StudentAdmission = %s has not been found", studentAdmissionUuid)))).collect(Collectors.toSet());

        //add all studentAdmission to student
        Set<Student> students = studentAdmissions.stream()
                .map( studentAdmission ->  {

                    //if studentAdmission already add to class(also has in student and user table)
                    if (studentAdmission.isStudent()) {

                        //get student from student table
                        Student student =
                                studentRepository.findByUuid(studentAdmission.getStudentUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Student = %s has not been found",studentAdmission.getStudentUuid())));


                        //get all class that student study
                        Set<Class> classes=student.getClasses();

                        //add new class to set of class of student
                        classes.add(aClass);

                        //set classes to student
                        student.setClasses(classes);

                        return student;
                    }
                    //admission that doesn't add to class yet(not student)
                    else{

                        //map from student admission
                        Student student = studentAdmissionMapper.toStudent(studentAdmission);

                        if (userRepository.existsByEmailOrUsername(student.getUser().getEmail(), student.getUser().getUsername())) {
                            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("UserName = %s or %s has " +
                                    "already exist", student.getUser().getEmail(), student.getUser().getUsername()));
                        }

                        //set uuid to student
                        student.setUuid(UUID.randomUUID().toString());

                        // Map user request to user
                        User user = student.getUser();

                        //set uuid to user
                        user.setUuid(UUID.randomUUID().toString());

                        //random password for user(student)
                        user.setRawPassword(generateStrongPassword());

                        user.setIsDeleted(false);
                        user.setStatus(false);

                        //set userName to user
                        user.setUsername(studentAdmission.getNameEn().trim().replaceAll("\\s+", "-") + "-" + studentAdmission.getDob());

                        //set user information
                        user.setIsChangePassword(false);
                        user.setAccountNonExpired(true);
                        user.setAccountNonLocked(true);
                        user.setCredentialsNonExpired(true);

                        //set default authorities to user
                        user.setAuthorities(studentService.getDefaultAuthoritiesStudent());

                        // set user to student
                        student.setUser(userRepository.save(user));


                        //set student uuid to admission
                        studentAdmission.setStudentUuid(student.getUuid());

                        //set isStudent true(mark for admission that already add to student)
                        studentAdmission.setStudent(true);


                        return student;
                    }

                })
                .collect(Collectors.toSet());

        //save student to database
        studentRepository.saveAll(students);

        //save all studentAdmission to database
        studentAdmissionRepository.saveAll(studentAdmissions);

        //get all student in class
        Set<Student> allStudents = aClass.getStudents();

        //add new student from request
        allStudents.addAll(students);

        //set student to class(include old and new student)
        aClass.setStudents(allStudents);

        //save to database
        classRepository.save(aClass);

        //map to DTO and return
        return classMapper.toClassDetailResponse(aClass);
    }

    @Override
    public void deleteStudent(String uuid, String studentUuid) {

        //validate class from DTO
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found", uuid)));

        //find all student in database by  student uuid
        Student student =
                studentRepository.findStudentByUserUuid(studentUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Student = %s has not been found in database", studentUuid)));

        //get all student from class
        Set<Student> allStudents = aClass.getStudents();

        //check for student is existed in class or not
        if (allStudents == null || !allStudents.contains(student)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student = %s has not been found " +
                    "in class %s", studentUuid, uuid));
        }
        //remove student from class
        allStudents.remove(student);

        //set new Set of student after remove to class
        aClass.setStudents(allStudents);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public void publicClassByUuid(String uuid) {

        //validate class from dto by uuid
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found ! ", uuid)));

        //set isDraft to false(public)
        aClass.setIsDraft(false);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public void draftClassByUuid(String uuid) {

        //validate class from dto by uuid
        Class aClass =
                classRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found ! ", uuid)));

        //set isDraft to true(draft)
        aClass.setIsDraft(true);

        //save to database
        classRepository.save(aClass);
    }

    private static String generateStrongPassword() {
        CharacterRule lowercaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule uppercaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);

        PasswordGenerator generator = new PasswordGenerator();

        return generator.generatePassword(10, Arrays.asList(
                lowercaseRule,
                uppercaseRule,
                digitRule,
                specialCharRule
        ));
    }

}