package co.istad.lms.mapper;


import co.istad.lms.domain.Attendance;
import co.istad.lms.features.attendance.dto.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses=StudentMapper.class)
public interface AttendanceMapper {

    Attendance fromAttendanceRequest(AttendanceRequest degreeCreateRequest);

    AttendanceDetailResponse toAttendanceDetailResponse(Attendance attendance);

    AttendanceResponse toAttendanceResponse(Attendance attendance);

    AttendanceInstructorReportResponse toAttendanceInstructorReportResponse(Attendance attendance);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAttendanceFromRequest(@MappingTarget Attendance attendance, AttendanceUpdateRequest attendanceUpdateRequest);


}
