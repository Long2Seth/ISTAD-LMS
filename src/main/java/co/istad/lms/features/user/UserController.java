package co.istad.lms.features.user;

import co.istad.lms.features.user.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;



        @GetMapping("/view-profile")
    public UserProfile viewProfile(){
        return  userService.viewProfile();
    }





    @PreAuthorize("hasAuthority('admin:control')")
    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return userService.getAllUsers(pageNumber, pageSize);
    }




    @PreAuthorize("hasAuthority('admin:control')")
    @GetMapping("not-students")
    public Page<UserResponse> getAllUsersExceptStudents(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return userService.getAllUsersExceptStudents(pageNumber, pageSize);
    }




    @GetMapping("/authority/{uuid}")
    public AuthorityResponse getAllAuthorities(@PathVariable String uuid){
        return userService.viewsAuthorityAllUser(uuid);
    }




    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/admins")
    public Page<UserResponse> getAllUsersWithAdminRole(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return userService.getAllUsersWithAdminRole(pageNumber, pageSize);
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
    @PatchMapping("/{uuid}")
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
    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUser(@PathVariable String uuid){
        userService.disableUser(uuid);
    }





    @PreAuthorize("hasAnyAuthority('user:update')")
    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableUser(@PathVariable String uuid){
        userService.enableUser(uuid);
    }




    @PreAuthorize("hasAnyAuthority('user:update')")
    @PutMapping("/{uuid}/block")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void blockUser(@PathVariable String uuid){
         userService.isDeleted(uuid);
    }




}
