package co.istad.lms.mapper;

import co.istad.lms.domain.Class;
import co.istad.lms.features.classes.dto.ClassDetailResponse;
import co.istad.lms.features.classes.dto.ClassRequest;
import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.classes.dto.ClassUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClassMapper {


//    @Mapping(target = "instructor",ignore = true)
    @Mapping(target = "studyProgram",ignore = true)
    @Mapping(target = "shift",ignore = true)
    @Mapping(target = "generation",ignore = true)
    @Mapping(target = "students",ignore = true)
    Class fromClassRequest(ClassRequest classRequest);

    ClassDetailResponse toClassDetailResponse(Class classes);

    ClassResponse toClassResponse(Class classes);

//    @Mapping(target = "instructor",ignore = true)
    @Mapping(target = "studyProgram",ignore = true)
    @Mapping(target = "shift",ignore = true)
    @Mapping(target = "generation",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClassFromRequest(@MappingTarget Class classes, ClassUpdateRequest classUpdateRequest);

}
