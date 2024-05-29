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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    private List<Authority> getAuthorities(List<AuthorityRequestToUser> authorityRequests) {
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityRequestToUser request : authorityRequests) {
            Authority authority = authorityRepository.findByAuthorityName(request.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Role with name = %s was not found.", request.authorityName())));
            authorities.add(authority);
        }
        return authorities;
    }


    private BirthPlace toBirthPlace(JsonBirthPlace birthPlaceRequest) {
        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(birthPlaceRequest.cityOrProvince());
        birthPlace.setKhanOrDistrict(birthPlaceRequest.khanOrDistrict());
        birthPlace.setSangkatOrCommune(birthPlaceRequest.sangkatOrCommune());
        birthPlace.setVillageOrPhum(birthPlaceRequest.villageOrPhum());
        birthPlace.setStreet(birthPlaceRequest.street());
        birthPlace.setHouseNumber(birthPlaceRequest.houseNumber());
        return birthPlace;
    }

    public void validateStaffRequest(StaffRequestDetail staffRequest) {
        if (userRepository.existsByEmailOrUsername(staffRequest.user().email() , staffRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", staffRequest.user().email())
            );
        }



    }


    @Override
    public StaffResponse createStaff(StaffRequest staffRequest) {


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

        List<Authority> authorities = getAuthorities(staffRequest.user().authorities());
        user.setAuthorities(authorities);
        // Save the user
        userRepository.save(user);

        // Create the staff entity
        Staff staff = staffMapper.toRequest(staffRequest);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setPosition(staffRequest.position());
        staff.setUser(user);
        staffRepository.save(staff);

        return staffMapper.toResponse(staff);

    }

    @Override
    public StaffResponse updateStaffByUuid(String uuid, StaffRequestDetail staffRequestDetail) {

        // Check if the staff exists
        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        // Check if the user exists
        User user = userRepository.findByUuid(staffRequestDetail.user().uuid())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with alias = %s was not found.", staffRequestDetail.user().uuid())
                        )
                );

        // Check if the user exists
        validateStaffRequest(staffRequestDetail);

        // Update the user
        staff.setPosition(staffRequestDetail.position());
        user.setNameEn(staffRequestDetail.user().nameEn());
        user.setNameKh(staffRequestDetail.user().nameKh());
        user.setEmail(staffRequestDetail.user().email());
        user.setPhoneNumber(staffRequestDetail.user().phoneNumber());
        user.setGender(staffRequestDetail.user().gender());
//        user.setCityOrProvince(staffRequestDetail.user()().cityOrProvince());
//        user.setKhanOrDistrict(staffRequestDetail.user()().khanOrDistrict());
//        user.setSangkatOrCommune(staffRequestDetail.user()().sangkatOrCommune());
//        user.setStreet(staffRequestDetail.user()().street());
//        user.setProfileImage(staffRequestDetail.user()().profileImage());
//        user.setBirthPlace(toBirthPlace(staffRequestDetail.user()().birthPlace()));

        // Update the authorities
        List<Authority> authorities = getAuthorities(staffRequestDetail.user().authorities());
        user.setAuthorities(authorities);

        // Assign authorities to the user
        user.setAuthorities(authorities);
        // Save the user
        userRepository.save(user);
        // Save the staff
        staffRepository.save(staff);

        return staffMapper.toResponse(staff);

    }



    @Override
    public StaffResponse getStaffByUuid(String uuid) {

        return staffRepository.findByUuid(uuid)
                .map(staffMapper::toResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

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
    public Page<StaffResponse> getStaffs(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Staff> staff = staffRepository.findAll(pageRequest);
        List<Staff> staffs = staff.stream()
                .filter(s -> !s.isDeleted())
                .filter(s -> !s.isStatus())
                .toList();

        return new PageImpl<>(staffs, pageRequest, staffs.size()).map(staffMapper::toResponse);

    }

    @Override
    public StaffResponse disableByUuid(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );
        staff.setStatus(false);
        staffRepository.save(staff);
        return staffMapper.toResponse(staff);
    }

    @Override
    public StaffResponse enableByUuid(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );
        staff.setStatus(true);
        staffRepository.save(staff);
        return staffMapper.toResponse(staff);


    }

    @Override
    public StaffResponse updateDeletedStatus(String uuid) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );
        staff.setDeleted(!staff.isDeleted());
        staffRepository.save(staff);
        return staffMapper.toResponse(staff);

    }

}
