package co.istad.lms.features.attendance.dto;

import java.util.Set;

public record AttendanceMultipleRowCreateRequest(
        Set<AttendanceRequest> attendances
) {
}
