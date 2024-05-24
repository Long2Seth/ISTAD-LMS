package co.istad.lms.features.attendance.dto;

import lombok.Builder;

@Builder
public record AttendanceResponse(

        String uuid,
        String note,
        String studentAlias,
        String lectureAlias

) {
}
/*
@Column(nullable = false, unique = true)
private String uuid;

@Column(nullable = false)
private Boolean status;

private String note;

@Column(nullable = false)
private String studentAlias;

@Column(nullable = false)
private String lectureAlias;*/