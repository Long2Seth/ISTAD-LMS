package co.istad.lms.features.classes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Set;

public record ClassAddStudentRequest(

        @NotNull(message = "studentAdmissionUuid is require")
        Set<String> studentAdmissionUuid
) {
}
