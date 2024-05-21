package co.istad.lms.features.user;


import co.istad.lms.base.BasedResponse;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit
    ){
        return userService.getAllUsers(page, limit);
    }


    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}/disable")
    public UserResponse disableUser(@PathVariable Long id){
        return userService.disableUser(id);
    }

    @PatchMapping("/{id}/enable")
    public UserResponse enableUser(@PathVariable Long id){
        return userService.enableUser(id);
    }

    @PatchMapping("/{id}/block")
    public UserResponse isDeleted(@PathVariable Long id){
        return userService.isDeleted(id);
    }


}
