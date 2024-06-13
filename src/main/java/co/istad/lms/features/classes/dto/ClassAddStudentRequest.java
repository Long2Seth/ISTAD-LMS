package co.istad.lms.features.classes.dto;

import java.util.Set;

public record ClassAddStudentRequest(

        Set<String> studentAdmissionUuid
) {
}
