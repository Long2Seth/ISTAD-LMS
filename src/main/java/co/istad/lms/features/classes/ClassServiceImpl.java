package co.istad.lms.features.classes;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.domain.Class;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.classes.dto.ClassDetailResponse;
import co.istad.lms.features.classes.dto.ClassRequest;
import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.classes.dto.ClassUpdateRequest;
import co.istad.lms.features.generation.GenerationRepository;
import co.istad.lms.features.instructor.InstructorRepository;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
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

    @Override
    public void createClass(ClassRequest classRequest) {

        //validate class by alias
        if (classRepository.existsByAlias(classRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT,

                    String.format("Class = %s has already exited", classRequest.alias()));
        }

        //find studyProgram by studyPramAlias in classRequest
        StudyProgram studyProgram = studyProgramRepository.findByAlias(classRequest.studyProgramAlias())

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("StudyProgram = %s has not been found", classRequest.studyProgramAlias())));

        //map from DTO to entity
        Class aClass = classMapper.fromClassRequest(classRequest);

//        find instructor by instructorUuid in classRequest
        if (classRequest.instructorUuid() != null) {

            Instructor instructor = instructorRepository.findByUuid(classRequest.instructorUuid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Instructor = %s has not been found", classRequest.instructorUuid())));

            //set instructor to class
            aClass.setInstructor(instructor);
        }

        //find shift by shiftAlias in classRequest
        Shift shift = shiftRepository.findByAlias(classRequest.shiftAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Shift = %s has not been found", classRequest.shiftAlias())));

        //find generation by generationAlias in classRequest
        Generation generation = generationRepository.findByAlias(classRequest.generationAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Generation = %s has not been found", classRequest.generationAlias())));
        //find student by studentUuid from dto
        if (classRequest.studentUuid() != null) {

            Set<Student> students = classRequest.studentUuid().stream()
                    .map(studentUui -> studentRepository.findByUuid(studentUui)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Student with uuid = %s has not been found.", studentUui))))
                    .collect(Collectors.toSet());

            //set student to class
            aClass.setStudents(students);
        }

        //set shift to entity
        aClass.setShift(shift);

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
    public ClassDetailResponse getClassByAlias(String alias) {

        //find class by alias
        Class aClass = classRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Class = %s has not been found.", alias)));

        //return degree detail
        return classMapper.toClassDetailResponse(aClass);
    }

    @Override
    public Page<ClassDetailResponse> getAllClasses(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all classes in database
        Page<Class> classes = classRepository.findAll(pageRequest);

        //map entity to DTO and return
        return classes.map(classMapper::toClassDetailResponse);
    }

    @Override
    public ClassDetailResponse updateClassByAlias(String alias, ClassUpdateRequest classUpdateRequest) {

        //validate class from DTO
        Class aClass = classRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Class = %s has not been found", alias)));

        //check studyProgram from update DTO
        if (classUpdateRequest.studyProgramAlias() != null) {

            //find studyProgram by studyPramAlias in classRequest
            StudyProgram studyProgram = studyProgramRepository.findByAlias(classUpdateRequest.studyProgramAlias())

                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                            String.format("StudyProgram = %s has not been found", classUpdateRequest.studyProgramAlias())));

            //set studyProgram to entity
            aClass.setStudyProgram(studyProgram);

        }

        //check instructor from update DTO
        if (classUpdateRequest.instructorUuid() != null) {

            //find instructor by instructorUuid in classRequest
            Instructor instructor = instructorRepository.findByUuid(classUpdateRequest.instructorUuid())

                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                            String.format("Instructor = %s has not been found", classUpdateRequest.instructorUuid())));

            //set instructor to entity
//            aClass.setInstructor(instructor);
        }


        //check shift from update DTO
        if (classUpdateRequest.shiftAlias() != null) {

            //find shift by shiftAlias in classRequest
            Shift shift = shiftRepository.findByAlias(classUpdateRequest.shiftAlias())

                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                            String.format("Shift = %s has not been found", classUpdateRequest.shiftAlias())));

            //set shift to entity
            aClass.setShift(shift);
        }

        //check generation from update DTO
        if (classUpdateRequest.generationAlias() != null) {

            //find generation by generationAlias in classRequest
            Generation generation = generationRepository.findByAlias(classUpdateRequest.generationAlias())

                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                            String.format("Generation = %s has not been found", classUpdateRequest.generationAlias())));


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
    public void deleteClassByAlias(String alias) {

        //find class in database by alias
        Class aClass = classRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Class = %s has not been found.", alias)));

        //delete class in database
        classRepository.delete(aClass);
    }

    @Override
    public void enableClassByAlias(String alias) {

        //validate class from dto by alias
        Class aClass = classRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Class = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        aClass.setIsDeleted(false);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public void disableClassByAlias(String alias) {

        //validate class from dto by alias
        Class aClass = classRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Class = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        aClass.setIsDeleted(true);

        //save to database
        classRepository.save(aClass);
    }

    @Override
    public Page<ClassDetailResponse> filterClasses(BaseSpecification.FilterDto filterDto, int page, int size) {


        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Class entities based on the criteria provided
        Specification<Class> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Class> classes = classRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return classes.map(classMapper::toClassDetailResponse);

    }
}