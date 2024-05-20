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
    public BasedResponse<Page<UserResponse>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit
    ){
        return BasedResponse.<Page<UserResponse>>ok()
                .setPayload(userService.getAllUsers(page, limit));
    }


    @PostMapping("/create-user")
    public BasedResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.createUser(userRequest));
    }

    @PutMapping("/update-user/{id}")
    public BasedResponse<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/delete-user/{id}")
    public BasedResponse<UserResponse> deleteUser(@PathVariable Long id){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.deleteUser(id));
    }

    @GetMapping("/get-user/{id}")
    public BasedResponse<UserResponse> getUserById(@PathVariable Long id){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @PatchMapping("/disable-user/{id}")
    public BasedResponse<UserResponse> disableUser(@PathVariable Long id){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.disableUser(id));
    }

    @PatchMapping("/enable-user/{id}")
    public BasedResponse<UserResponse> enableUser(@PathVariable Long id){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.enableUser(id));
    }

    @PatchMapping("/is-deleted/{id}")
    public BasedResponse<UserResponse> isDeleted(@PathVariable Long id){
        return BasedResponse.<UserResponse>ok()
                .setPayload(userService.isDeleted(id));
    }


}
