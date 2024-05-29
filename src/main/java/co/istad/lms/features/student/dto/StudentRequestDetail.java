package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.UserRequestDetail;

public record StudentRequestDetail (
        UserRequestDetail user
){
}
