package co.istad.lms.features.user.dto;

import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record UserRequest(


        @NotBlank(message = "English name is required")
        @Size(max = 50, message = "English name must be less than or equal to 50 characters")
        String nameEn,

        @NotBlank(message = "Khmer name is required")
        @Size(max = 50, message = "Khmer name must be less than or equal to 50 characters")
        String nameKh,

        @NotBlank(message = "Username is required")
        @Size(max = 50, message = "Username must be less than or equal to 50 characters")
        String username,

        @NotBlank(message = "Gender is required")
        @Size(max = 10, message = "Gender must be less than or equal to 10 characters")
        String gender,

        @NotNull(message = "Date of birth is required")
        LocalDate dob,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be less than or equal to 100 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Profile image is required")
        @Size(max = 255, message = "Profile image must be less than or equal to 255 characters")
        String profileImage,

        @Size(max = 20, message = "Phone number must be less than or equal to 20 characters")
        String phoneNumber,

        @Valid
        Set<AuthorityRequestToUser> authorities
) {
}
