package co.istad.lms.features.staff;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.staff.dto.*;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.StaffMapper;
import co.istad.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createStaff(StaffRequest staffRequest) {


        if (userRepository.existsByUsername(staffRequest.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with username = %s already exists", staffRequest.username())
            );
        }

        // Save the user first
        User user = userMapper.fromStaffRequest(staffRequest);
        user.setPassword(passwordEncoder.encode(staffRequest.password()));
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setStatus(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Set<Authority> allAuthorities = new HashSet<>();
        for (String authorityName : staffRequest.authorityNames()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(authorityName);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);

        // Save the user
        userRepository.save(user);

        // Create the staff entity
        Staff staff = staffMapper.toRequest(staffRequest);
        staff.setUuid(UUID.randomUUID().toString());

        staff.setUser(user);

        // Save the staff
        staffRepository.save(staff);


    }

    @Override
    public StaffResponseDetail updateStaffByUuid(String uuid, StaffRequestUpdate staffRequestUpdate) {

        // Check if the user exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );



        // Check if the user exists
        if (userRepository.existsByEmailOrUsernameAndUuidNot(staffRequestUpdate.email(), staffRequestUpdate.username() ,uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", staffRequestUpdate.email(), staffRequestUpdate.username())
            );
        }

        // Update the user
        userMapper.updateUserFromStaffRequest( user , staffRequestUpdate);

        // Save the user
        userRepository.save(user);
        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );

        // Update the staff
        staff.setUser(user);

        // Save the staff
        Staff saveStaff = staffRepository.save(staff);

        staffMapper.updateStaffFromRequest(saveStaff, staffRequestUpdate);

        return staffMapper.toResponseDetail(staff);

    }





    @Override
    public Page<StaffResponseDetail> getStaffDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Staff> staff = staffRepository.findAll(pageRequest);
        List<Staff> staffs = staff.stream()
                .filter(s -> !s.getUser().getStatus())
                .filter(s -> !s.getUser().getIsDeleted())
                .toList();

        return new PageImpl<>(staffs, pageRequest, staffs.size()).map(staffMapper::toResponseDetail);


    }




    @Override
    public Page<StaffResponse> getStaff(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Staff> staff = staffRepository.findAll(pageRequest);
        List<Staff> staffs = staff.stream()
                .filter(s -> !s.getUser().getStatus())
                .filter(s -> !s.getUser().getIsDeleted())
                .toList();

        return new PageImpl<>(staffs, pageRequest, staffs.size()).map(staffMapper::toResponse);

    }




    @Override
    public StaffResponseDetail getStaffDetailByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );
        
        return staffMapper.toResponseDetail(staff);

    }




    @Override
    public StaffResponse getStaffByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );


        return staffMapper.toResponse(staff);
    }





    @Override
    public void deleteStaffByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );

        // Delete the user
        staffRepository.delete(staff);

    }






    @Override
    public void disableByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );

        // Change status is true
        user.setStatus(true);

        // Save the staff
        staffRepository.save(staff);

    }





    @Override
    public void enableByUuid(String uuid) {


        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );

        // Change status is false
        user.setStatus(false);

        // Save the staff
        staffRepository.save(staff);

    }




    @Override
    public void updateDeletedStatus(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", uuid)
                        )
                );

        Staff staff = staffRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with user = %s was not found.", user)
                        )
                );

        // Change isDeleted is true
        user.setIsDeleted(true);

        // Save the staff
        staffRepository.save(staff);

    }




}
