package co.istad.lms.features.instructor;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.instructor.dto.*;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.InstructorMapper;
import co.istad.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {


    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createInstructor(InstructorRequest instructorRequest) {

        // Check if the username or email already exists from database
        if (userRepository.existsByEmailOrUsername(instructorRequest.email(), instructorRequest.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s have already exists", instructorRequest.email(), instructorRequest.username())
            );
        }



        // Create new user for the instructor
        User user = userMapper.fromInstructorRequest(instructorRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(instructorRequest.password()));
        user.setIsDeleted(false);
        user.setStatus(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Set the authorities for the user
        Set<Authority> allAuthorities = new HashSet<>();
        for (String authorityName : instructorRequest.authorityNames()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(authorityName);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);

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
                        String.format("User with uuid = %s not found",uuid)
                ));



        // Check if the username or email already exists from database except the current user
        if (userRepository.existsByEmailOrUsernameAndUuidNot(instructorRequestUpdate.username(), instructorRequestUpdate.email() , user.getUuid())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", instructorRequestUpdate.email())
            );
        }


        // Update the user from the instructor request
        userMapper.updateUserFromInstructorRequest(user, instructorRequestUpdate);

        // Save the updated user to the database
        userRepository.save(user);

        // Find the instructor by its UUID and user
        Instructor instructor = instructorRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found",uuid)
                ));

        // Save the updated user and instructor
        instructor.setUser(user);

        // Save the updated instructor to the database
        Instructor savedAdmin = instructorRepository.save(instructor);

        // Update the instructor from the instructor request
        instructorMapper.updateInstructorFromRequest(savedAdmin, instructorRequestUpdate);

        // Save the updated instructor to the database
        return instructorMapper.toResponseDetail(savedAdmin);


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
    public Page<InstructorResponse> getAllInstructor(String search, int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Instructor> instructorsRequest = instructorRepository.findAll(pageRequest);
        List<Instructor> instructors = instructorsRequest.stream()
                .filter(instructor -> !instructor.getUser().getIsDeleted())
                .filter(instructor -> !instructor.getUser().getStatus())
                .toList();
        Page<Instructor> filteredInstructors = new PageImpl<>(instructors, pageRequest, instructors.size());

        return filteredInstructors.map(instructorMapper::toResponse);
    }


}
