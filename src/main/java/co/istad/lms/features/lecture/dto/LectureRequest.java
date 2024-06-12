package co.istad.lms.features.lecture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LectureRequest(

            @NotBlank(message = "Start time is required")
            String startTime,
            String endTime,
            String description,
            @NotBlank(message = "Lecture date is required")
            String lectureDate,
            Boolean status,

            String courseUuid,
            @NotNull(message = "isDraft is required")
            Boolean isDraft
) {
}

/*

@Column(nullable = false,unique = true)
private String alias;

@Column(nullable = false)
private String startTime;

@Column(nullable = false)
private String endTime;

private String description;

@Column(nullable = false)
private LocalDate lectureDate;

@Column(nullable = false)
private Boolean status;

@Column(nullable = false)
private Boolean isDeleted;

@Column(nullable = false)
private String courseAlias;*/