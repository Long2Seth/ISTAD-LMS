package co.istad.lms.features.staff;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.staff.dto.StaffRequest;
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


    private User setUpNewUser(StaffRequest studentRequest) {
        var userReq = studentRequest.userRequest();
        User user = new User();

        user.setAlias(userReq.alias());
        user.setEmail(userReq.email());
        user.setPhoneNumber(userReq.phoneNumber());
        user.setPassword(passwordEncoder.encode(userReq.password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(userReq.gender());
        user.setNameEn(userReq.nameEn());
        user.setNameKh(userReq.nameKh());
        user.setUsername(userReq.username());
        user.setProfileImage(userReq.profileImage());
        user.setCityOrProvince(userReq.cityOrProvince());
        user.setKhanOrDistrict(userReq.khanOrDistrict());
        user.setSangkatOrCommune(userReq.sangkatOrCommune());
        user.setStreet(userReq.street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setBirthPlace(toBirthPlace(userReq.birthPlace()));

        List<Authority> authorities = new ArrayList<>();
        studentRequest.userRequest().authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Role with name = %s was not found.", r.authorityName())
                            )
                    );

            authorities.add(authority);

        });

        // Assign authorities to the user
        user.setAuthorities(authorities);

        return user;
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


    @Override
    public StaffResponse createStaff(StaffRequest staffRequest) {

        if (userRepository.existsByAlias(staffRequest.userRequest().alias())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", staffRequest.userRequest().alias())
            );
        }

        if (userRepository.existsByEmail(staffRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", staffRequest.userRequest().email())
            );
        }

        if (userRepository.existsByPhoneNumber(staffRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", staffRequest.userRequest().phoneNumber())
            );
        }

        // Save the user first
        User user = setUpNewUser(staffRequest);
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
    public StaffResponse updateStaffByUuid(String uuid, StaffRequest staffRequest) {

        Staff staff = staffRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Staff with uuid = %s was not found.", uuid)
                        )
                );

        if (userRepository.existsByPhoneNumber(staffRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", staffRequest.userRequest().phoneNumber())
            );
        }

        if (userRepository.existsByEmail(staffRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", staffRequest.userRequest().email())
            );
        }

        String alias = staffRequest.userRequest().alias();
        User user = userRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with alias = %s was not found.", alias)
                        )
                );

        staff.setPosition(staffRequest.position());
        user.setNameEn(staffRequest.userRequest().nameEn());
        user.setNameKh(staffRequest.userRequest().nameKh());
        user.setEmail(staffRequest.userRequest().email());
        user.setPhoneNumber(staffRequest.userRequest().phoneNumber());
        user.setGender(staffRequest.userRequest().gender());
        user.setCityOrProvince(staffRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(staffRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(staffRequest.userRequest().sangkatOrCommune());
        user.setStreet(staffRequest.userRequest().street());
        user.setProfileImage(staffRequest.userRequest().profileImage());
        user.setBirthPlace(toBirthPlace(staffRequest.userRequest().birthPlace()));


        List<Authority> authorities = new ArrayList<>();
        staffRequest.userRequest().authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Role with name = %s was not found.", r.authorityName())
                            )
                    );

            authorities.add(authority);

        });

        // Assign authorities to the user
        user.setAuthorities(authorities);
        userRepository.save(user);
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
        String alias = staff.getUser().getAlias();
        // Delete the staff
        staffRepository.delete(staff);

        User user = userRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s was not found.", alias)
                        )
                );
        // Delete the user
        userRepository.delete(user);

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

        int updated = staffRepository.updateStatusById(uuid, false);
        if (updated > 0)
        {
            return staffRepository.findByUuid(uuid)
                    .map(staffMapper::toResponse)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Staff with uuid = %s was not found.", uuid)
                            )
                    );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Staff with uuid = %s not found", uuid));
        }
    }

    @Override
    public StaffResponse enableByUuid(String uuid) {
        int updated = staffRepository.updateStatusById(uuid, true);
        if (updated > 0)
        {
            return staffRepository.findByUuid(uuid)
                    .map(staffMapper::toResponse)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Staff with uuid = %s was not found.", uuid)
                            )
                    );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Staff with uuid = %s not found", uuid));
        }


    }

    @Override
    public StaffResponse updateDeletedStatus(String uuid) {
        int updated = staffRepository.updateDeletedStatusById(uuid, true);
        if (updated > 0)
        {
            return staffRepository.findByUuid(uuid)
                    .map(staffMapper::toResponse)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Staff with uuid = %s was not found.", uuid)
                            )
                    );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Staff with uuid = %s not found", uuid));
        }
    }
}
