package co.istad.lms.features.instructor;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.Lecture;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.file.FileMetaDataRepository;
import co.istad.lms.features.instructor.dto.*;
import co.istad.lms.features.lecture.LectureRepository;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureInstructorScheduleResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.UserService;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.InstructorMapper;
import co.istad.lms.mapper.LectureMapper;
import co.istad.lms.mapper.UserMapper;
import co.istad.lms.util.DateTimeUtil;
import co.istad.lms.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {


    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final FileMetaDataRepository fileMetaDataRepository;

    private final LectureRepository lectureRepository;

    private final LectureMapper lectureMapper;


    public Set<Authority> getDefaultAuthorities() {
        // Set default authorities
        Set<Authority> authorities = new HashSet<>();
        authorities.addAll(authorityRepository.findAllByAuthorityName("session:read"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("session:write"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("session:update"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("message:read"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("message:write"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("message:update"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("course:read"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("course:write"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("payment:read"));

        return authorities;

    }


    @Override
    public void createInstructor(InstructorRequest instructorRequest) {

        // Check if the email already exists from database
        if (userRepository.existsByEmail(instructorRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s have already exists", instructorRequest.email())
            );
        }


        // Create new user for the instructor
        User user = userMapper.fromInstructorRequest(instructorRequest);

        user.setUuid(UUID.randomUUID().toString());
        user.setPosition("INSTRUCTOR");

        // Generate password rawPassword to encrypt
        String rawPassword = userService.generateStrongPassword(10);
        try {
            //generate key for encrypt
            SecretKey key = OtpUtil.generateKey();

            //encrypt password
            String encryptedPassword = OtpUtil.encryptOTP(rawPassword, key);

            //set raw password with encrypt password
            user.setRawPassword(encryptedPassword);
            // Set password to null
            user.setPassword(null);

        } catch (Exception e) {
            throw new RuntimeException("Error generating or encrypting password", e);
        }

        // Set dob from string to LocalDate that comes from the request validation
        LocalDate dob = DateTimeUtil.stringToLocalDate(instructorRequest.dob(), "dob");
        user.setDob(dob);

        user.setIsDeleted(false);
        user.setStatus(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setUsername(instructorRequest.nameEn().trim().replaceAll("\\s+", "-") + "-" + instructorRequest.dob());
        user.setAuthorities(getDefaultAuthorities());
        // Save the user and instructor
        userRepository.save(user);

        // Create new instructor
        Instructor instructor = instructorMapper.toRequest(instructorRequest);
        instructor.setUuid(UUID.randomUUID().toString());

        // Save the user and instructor
        instructor.setUser(user);

        // Save the instructor to the database
        instructorRepository.save(instructor);

    }


    @Override
    public InstructorResponseDetail updateInstructorByUuid(String uuid, InstructorRequestUpdate instructorRequestUpdate) {
        // Find the user by its UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        if (instructorRequestUpdate.profileImage() != null && !instructorRequestUpdate.profileImage().trim().isEmpty() && !fileMetaDataRepository.existsByFileName(instructorRequestUpdate.profileImage())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("File with name = %s not found!", instructorRequestUpdate.profileImage()));
        }

        // Check if the email already exists in the database except for the current user
        if (userRepository.existsByEmail(instructorRequestUpdate.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", instructorRequestUpdate.email())
            );
        }

        // Update the user from the instructor request
        userMapper.updateUserFromInstructorRequest(user, instructorRequestUpdate);

        // Save the updated user to the database
        userRepository.save(user);

        // Find the instructor by its user
        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor associated with user uuid = %s not found", uuid)
                ));

        // Update the instructor from the instructor request
        instructorMapper.updateInstructorFromRequest(instructor, instructorRequestUpdate);

        // Save the updated instructor to the database
        Instructor savedInstructor = instructorRepository.save(instructor);

        // Return the updated instructor details
        return instructorMapper.toResponseDetail(savedInstructor);
    }

    @Override
    public InstructorResponseDetail getInstructorDetailByUuid(String uuid) {

        // Find the user by its UUID and throw an exception if not found
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        // Find the instructor by its user and throw an exception
        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Instructor with uuid = %s not found", uuid)
                        )
                );

        // Return the instructor response detail
        return instructorMapper.toResponseDetail(instructor);

    }


    @Override
    public InstructorResponse getInstructorByUuid(String uuid) {

        // Find the user by its UUID and throw an exception if not found
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );


        // Find the instructor by its user and throw an exception
        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Instructor with uuid = %s not found", uuid)
                        )
                );

        // Return the instructor response
        return instructorMapper.toResponse(instructor);


    }


    @Override
    public void deleteInstructorByUuid(String uuid) {


        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Instructor with uuid = %s not found", uuid)
                        )
                );

        instructorRepository.delete(instructor);

    }

    @Override
    public void disableInstructorByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        // Set status to false
        user.setStatus(false);

        // Save the instructor
        instructorRepository.save(instructor);


    }

    @Override
    public void enableInstructorByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        // Set status to false
        user.setStatus(false);

        // Save the instructor
        instructorRepository.save(instructor);

    }


    // This method that deleted the instructor by its UUIDby soft delete
    @Override
    public void blockInstructorByUuid(String uuid) {

        // Find the instructor by its UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        // Find the instructor by its UUID
        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        // Block the instructor
        user.setIsDeleted(true);

        // Save the instructor
        instructorRepository.save(instructor);

    }

    @Override
    public Page<InstructorResponseDetail> getAllInstructorDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Instructor> instructorsRequest = instructorRepository.findAll(pageRequest);
        List<Instructor> instructors = instructorsRequest.stream()
                .filter(instructor -> !instructor.getUser().getIsDeleted())
                .filter(instructor -> !instructor.getUser().getStatus())
                .toList();
        Page<Instructor> filteredInstructors = new PageImpl<>(instructors, pageRequest, instructors.size());

        return filteredInstructors.map(instructorMapper::toResponseDetail);

    }


    @Override
    public Page<InstructorResponse> getAllInstructor(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Instructor> instructorsRequest = instructorRepository.findAll(pageRequest);
        List<Instructor> instructors = instructorsRequest.stream()
                .filter(instructor -> !instructor.getUser().getIsDeleted())
                .filter(instructor -> !instructor.getUser().getStatus())
                .toList();
        Page<Instructor> filteredInstructors = new PageImpl<>(instructors, pageRequest, instructors.size());

        return filteredInstructors.map(instructorMapper::toResponse);
    }

    @Override
    public Page<LectureInstructorScheduleResponse> getAllSchedule(String userUuid, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "lectureDate");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all lecture in database
        Page<Lecture> lectures = lectureRepository.findAllByCourseInstructorUserUuid(userUuid,pageRequest);

        // map to DTO and return
        return lectures.map(lecture -> {

            String classCode = lecture.getCourse().getOneClass().getClassCode();

            String startTime=DateTimeUtil.localTimeToString(lecture.getStartTime());

            String endTime=DateTimeUtil.localTimeToString(lecture.getEndTime());

            return lectureMapper.toLectureInstructorScheduleResponse(lecture, classCode,startTime,endTime);
        });
    }


}
