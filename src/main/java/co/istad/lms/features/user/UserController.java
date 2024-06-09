package co.istad.lms.features.user;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;


    @PreAuthorize("hasAuthority('admin:control')")
    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return userService.getAllUsers(pageNumber, pageSize);
    }




    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/admins")
    public Page<UserResponse> getAllUsersWithAdminRole(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ) {
        return userService.getAllUsersWithAdminRole(page, limit);
    }




    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/details")
    public Page<UserResponseDetail> getAllUsersDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return userService.getAllUsersDetail(pageNumber, pageSize);
    }



    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return  userService.createUser(userRequest);
    }




    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/{uuid}")
    public UserResponse updateUser(@PathVariable String uuid, @Valid @RequestBody UserUpdateRequest userRequest){
        return userService.updateUser(uuid,userRequest);
    }




    @PreAuthorize("hasAuthority('user:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteUser(@PathVariable String uuid){
        userService.deleteUser(uuid);
    }




    @PreAuthorize("hasAnyAuthority('user:read')")
    @GetMapping("/{uuid}")
    public UserResponse getUserById(@PathVariable String uuid){
        return userService.getUserById(uuid);
    }




    @PreAuthorize("hasAnyAuthority('user:read')")
    @GetMapping("/details/{uuid}")
    public UserResponseDetail getUserDetailById(@PathVariable String uuid){
        return userService.getUserDetailById(uuid);
    }




    @PreAuthorize("hasAnyAuthority('user:update')")
    @PatchMapping("/{uuid}/disable")
    public UserResponse disableUser(@PathVariable String uuid){
        return userService.disableUser(uuid);
    }





    @PreAuthorize("hasAnyAuthority('user:update')")
    @PatchMapping("/{uuid}/enable")
    public UserResponse enableUser(@PathVariable String uuid){
        return userService.enableUser(uuid);
    }




    @PreAuthorize("hasAnyAuthority('user:update')")
    @PatchMapping("/{uuid}/block")
    public UserResponse blockUser(@PathVariable String uuid){
        return userService.isDeleted(uuid);
    }




}
