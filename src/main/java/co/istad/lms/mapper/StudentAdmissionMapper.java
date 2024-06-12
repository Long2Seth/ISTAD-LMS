package co.istad.lms.mapper;

import co.istad.lms.domain.StudentAdmission;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionRequest;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentAdmissionMapper {

   @Mapping(target = "admission",ignore = true)
   @Mapping(target = "shift", ignore = true)
   @Mapping(target = "studyProgram", ignore = true)
   @Mapping(target = "degree", ignore = true)
    StudentAdmission fromStudentAdmissionRequest(StudentAdmissionRequest studentAdmissionRequest);

    StudentAdmissionResponse toStudentAdmissionResponse(StudentAdmission studentAdmission);


    StudentAdmissionDetailResponse toStudentAdmissionDetailResponse(StudentAdmission studentAdmission);


    @Mapping(target = "admission",ignore = true)
    @Mapping(target = "shift", ignore = true)
    @Mapping(target = "studyProgram", ignore = true)
    @Mapping(target = "degree", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentAdmissionFromRequest(@MappingTarget StudentAdmission studentAdmission,
                                           StudentAdmissionUpdateRequest studentAdmissionUpdateRequest);

    @Mapping(source = "nameEn", target = "user.nameEn")
    @Mapping(source = "nameKh", target = "user.nameKh")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "phoneNumber", target = "user.phoneNumber")
    @Mapping(source = "dob", target = "user.dob")
//    @Mapping(source = "pob", target = "user.pob")
//    @Mapping(source = "bacIiGrade", target = "user.bacIiGrade")
    @Mapping(source = "gender", target = "user.gender")
    @Mapping(source = "avatar", target = "user.profileImage")
    @Mapping(source = "address", target = "user.address")
//    @Mapping(source = "guardianContact", target = "user.guardianContact")
//    @Mapping(source = "guardianRelationShip", target = "user.guardianRelationShip")
//    @Mapping(source = "knownIstad", target = "user.knownIstad")
//    @Mapping(source = "identity", target = "user.identity")
    @Mapping(source = "biography", target = "user.biography")
    Student toStudent(StudentAdmission studentAdmission);
}
