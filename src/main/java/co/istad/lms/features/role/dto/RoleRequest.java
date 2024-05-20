package co.istad.lms.features.role.dto;

import lombok.Builder;

@Builder
public record RoleRequest(
        String roleName,
        String description
) {
}
