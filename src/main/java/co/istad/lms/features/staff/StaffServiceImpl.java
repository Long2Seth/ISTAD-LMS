package co.istad.lms.features.staff;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
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


        if (userRepository.existsByUsername(staffRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with username = %s already exists", staffRequest.user().username())
            );
        }

        // Save the user first
        User user = userMapper.fromUserRequest(staffRequest.user());
        user.setPassword(passwordEncoder.encode(staffRequest.user().password()));
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : staffRequest.user().authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            System.out.println("foundAuthorities = " + foundAuthorities);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);
        // Save the user
        userRepository.save(user);

        // Create the staff entity
        Staff staff = staffMapper.toRequest(staffRequest);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setPosition(staffRequest.position());
        staff.setUser(user);

        // Save the staff
        staffRepository.save(staff);


    }

    @Override
    public StaffResponseDetail updateStaffByUuid(String uuid, StaffRequestDetail staffRequestDetail) {

        // Check if the staff exists
        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        // Check if the user exists
        User user = userRepository.findByUuid(staff.getUser().getUuid())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", staff.getUser().getUuid())
                        )
                );

        // Check if the user exists
        if (userRepository.existsByEmailOrUsername(staffRequestDetail.user().email() , staffRequestDetail.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", staffRequestDetail.user().email() , staffRequestDetail.user().username())
            );
        }

        // Update the staff
        staffMapper.updateStaffFromRequest(staff, staffRequestDetail);

        if (staffRequestDetail.user().password() != null) {
            user.setPassword(passwordEncoder.encode(staffRequestDetail.user().password()));
        }

        // Set the authorities of the user from the authorities
        Set<Authority> allAuthorities = new HashSet<>();
        if (staffRequestDetail.user().authorities() == null || staffRequestDetail.user().authorities().isEmpty()) {
            for (Authority request : user.getAuthorities()) {
                Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.getAuthorityName());
                allAuthorities.addAll(foundAuthorities);
            }
        } else {
            for (AuthorityRequestToUser request : staffRequestDetail.user().authorities()) {
                Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
                allAuthorities.addAll(foundAuthorities);
            }
            user.setAuthorities(allAuthorities);
        }


        // Save the user
        userRepository.save(user);
        staff.setUser(user);

        // Save the staff
        staffRepository.save(staff);

        return staffMapper.toResponseDetail(staff);

    }



    @Override
    public StaffResponseDetail getStaffDetailByUuid(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );
        return staffMapper.toResponseDetail(staff);

    }

    @Override
    public StaffResponse getStaffByUuid(String uuid) {

            Staff staff = staffRepository.findByUuid(uuid)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Staff with uuid = %s was not found.", uuid)
                            )
                    );
            return staffMapper.toResponse(staff);
    }

    @Override
    public void deleteStaffByUuid(String uuid) {
        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );
        staffRepository.delete(staff);

    }

    @Override
    public Page<StaffResponseDetail> getStaffDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Staff> staff = staffRepository.findAll(pageRequest);
        List<Staff> staffs = staff.stream()
                .filter(s -> !s.isDeleted())
                .filter(s -> !s.isStatus())
                .toList();

        return new PageImpl<>(staffs, pageRequest, staffs.size()).map(staffMapper::toResponseDetail);

    }

    @Override
    public Page<StaffResponse> getStaff( int page, int limit) {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<Staff> staff = staffRepository.findAll(pageRequest);
            List<Staff> staffs = staff.stream()
                    .filter(s -> !s.isDeleted())
                    .filter(s -> !s.isStatus())
                    .toList();

            return new PageImpl<>(staffs, pageRequest, staffs.size()).map(staffMapper::toResponse);

    }

    @Override
    public void disableByUuid(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        // Change status is true
        staff.setStatus(true);

        // Save the staff
        staffRepository.save(staff);

    }

    @Override
    public void enableByUuid(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        // Change status is false
        staff.setStatus(false);

        // Save the staff
        staffRepository.save(staff);

    }

    @Override
    public void updateDeletedStatus(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        // Change deleted status is true
        staff.setDeleted(true);

        // Save the staff
        staffRepository.save(staff);

    }

}
