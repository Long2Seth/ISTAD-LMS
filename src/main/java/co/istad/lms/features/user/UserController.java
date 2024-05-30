package co.istad.lms.features.user;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
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
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ){
        return userService.getAllUsers(page,limit);
    }

    @PreAuthorize("hasAuthority('admin:control')")
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return  userService.createUser(userRequest);
    }


    @PreAuthorize("hasAuthority('admin:control')")
    @PutMapping("/{uuid}")
    public UserResponse updateUser(@PathVariable String uuid, @Valid @RequestBody UserRequest userRequest){
        return userService.updateUser(uuid,userRequest);
    }


    @PreAuthorize("hasAuthority('admin:control')")
    @DeleteMapping("/{uuid}")
    public UserResponse deleteUser(@PathVariable String uuid){
        return userService.deleteUser(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping("/{uuid}")
    public UserResponse getUserById(@PathVariable String uuid){
        return userService.getUserById(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public UserResponse disableUser(@PathVariable String uuid){
        return userService.disableUser(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public UserResponse enableUser(@PathVariable String uuid){
        return userService.enableUser(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/block")
    public UserResponse blockUser(@PathVariable String uuid){
        return userService.isDeleted(uuid);
    }


}
