package co.istad.lms.features.role.dto;

import lombok.Builder;

@Builder
public record RoleRequestToUser(
        String roleName
) {
}
