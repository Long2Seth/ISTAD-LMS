package co.istad.lms.features.role.dto;

import lombok.Builder;

@Builder
public record RoleResponse(
        Long id,
        String roleName,
        String description
) {
}
