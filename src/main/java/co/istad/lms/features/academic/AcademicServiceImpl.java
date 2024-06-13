package co.istad.lms.features.academic;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Academic;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.academic.dto.*;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.UserService;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.AcademicMapper;
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
public class AcademicServiceImpl implements AcademicService {



    private final AcademicRepository academicRepository;
    private final AcademicMapper academicMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserService userService;



    @Override
    public void createAcademic(AcademicRequest academicRequest) {

        // check if user with email or username already exists in the database
        // Check if the email already exists from database
        if (userRepository.existsByEmail(academicRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s have already exists", academicRequest.email())
            );
        }

        // Create academic by mapping
        Academic academic = academicMapper.toRequest(academicRequest);
        academic.setUuid(UUID.randomUUID().toString());

        // Create user by mapping
        User user = userMapper.fromAcademicRequest(academicRequest);
        // set user details
        user.setUuid(UUID.randomUUID().toString());
        user.setRawPassword(userService.generateStrongPassword(10));
        user.setPassword(passwordEncoder.encode(user.getRawPassword()));
        user.setUsername(academicRequest.nameEn().trim().replaceAll("\\s+", "-") + "-" + academicRequest.dob());        user.setStatus(false);
        user.setIsDeleted(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // set authorities to user that we get from the getAuthorities method
        Set<Authority> allAuthorities = new HashSet<>();
        for (String authorityName : academicRequest.authorityNames()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(authorityName);
            if (foundAuthorities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Authority with name = %s not found!", authorityName));
            }
            allAuthorities.addAll(foundAuthorities);
        }
        // set user to academic
        user.setAuthorities(allAuthorities);

        //save user and academic
        userRepository.save(user);
        // set user to academic
        academic.setUser(user);
        // save information to academic
        academicRepository.save(academic);

    }

    @Override
    public AcademicResponseDetail updateAcademicByUuid(String uuid, AcademicRequestUpdate academicRequestDetail) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with UUID = %s not found", uuid)));

        // Check if the user with the provided email or username already exists (excluding current user)
        if (userRepository.existsByEmailOrUsernameAndUuidNot(academicRequestDetail.email(), user.getUsername(), user.getUuid())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", academicRequestDetail.email(), user.getUsername()));
        }

        // Update the user fields from the academic request
        userMapper.updateUserFromAcademicRequest(user, academicRequestDetail);

        // Save the updated user entity
        userRepository.save(user);

        // Find the academic by the user
        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Set the updated user to the academic and save
        academic.setUser(user);
        academicMapper.updateAcademicFromRequest(academic, academicRequestDetail);
        Academic saveAcademic = academicRepository.save(academic);


        return academicMapper.toResponseDetail(saveAcademic);


    }

    @Override
    public AcademicResponseDetail getAcademicDetailByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        return academicMapper.toResponseDetail(academic);


    }

    @Override
    public AcademicResponse getAcademicsByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        return academicMapper.toResponse(academic);

    }

    @Override
    public void deleteAcademicByUuid(String uuid) {

        User user  =  userRepository.findByUuid(uuid)
                        .orElseThrow(
                                () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        String.format("User with uuid = %s not found", uuid)
                                )
                        );

        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academicRepository.delete(academic);

        academicMapper.toResponseDetail(academic);

    }

    @Override
    public void updateDisableAcademicByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)
                ));

        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        // set status to true
        user.setStatus(true);

        // save an academic
        academicRepository.save(academic);

    }

    @Override
    public void updateEnableAcademicByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );


        Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        // set status to false
        user.setStatus(false);

        // save an academic
        academicRepository.save(academic);

    }

    @Override
    public void updateDeletedAcademicByUuid(String uuid) {

       User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

         Academic academic = academicRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

         //set isDeleted to true
            user.setIsDeleted(true);

        // save an academic
        academicRepository.save(academic);

    }

    @Override
    public Page<AcademicResponseDetail> getAcademics(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Academic> academics = academicRepository.findAll(pageRequest);

        List<Academic> academicList = academics.stream()
                .filter(academic -> !academic.getUser().getIsDeleted())
                .filter(academic -> !academic.getUser().getStatus())
                .toList();

        Page<Academic> academicResponses = new PageImpl<>(academicList, pageRequest, academicList.size());
        return academicResponses.map(academicMapper::toResponseDetail);


    }


    @Override
    public Page<AcademicResponse> getAcademicsDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Academic> academics = academicRepository.findAll(pageRequest);

        List<Academic> academicList = academics.stream()
                .filter(academic -> !academic.getUser().getIsDeleted())
                .filter(academic -> !academic.getUser().getStatus())
                .toList();

        Page<Academic> academicResponses = new PageImpl<>(academicList, pageRequest, academicList.size());
        return academicResponses.map(academicMapper::toResponse);


    }


}
