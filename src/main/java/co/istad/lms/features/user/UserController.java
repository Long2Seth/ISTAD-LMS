package co.istad.lms.features.user;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int limit
    ){
        Page<UserResponse> users = userService.getAllUsers(page, limit);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{alias}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String alias, @Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(alias, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String alias){
        UserResponse userResponse = userService.deleteUser(alias);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userResponse);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String alias){
        UserResponse userResponse = userService.getUserById(alias);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PatchMapping("/{alias}/disable")
    public ResponseEntity<UserResponse> disableUser(@PathVariable String alias){
        UserResponse userResponse = userService.disableUser(alias);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PatchMapping("/{alias}/enable")
    public ResponseEntity<UserResponse> enableUser(@PathVariable String alias){
        UserResponse userResponse = userService.enableUser(alias);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PatchMapping("/{alias}/block")
    public ResponseEntity<UserResponse> blockUser(@PathVariable String alias){
        UserResponse userResponse = userService.isDeleted(alias);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
