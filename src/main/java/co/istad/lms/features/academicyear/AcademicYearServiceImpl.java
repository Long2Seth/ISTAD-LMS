package co.istad.lms.features.academicyear;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.academicyear.dto.AcademicYearDetailResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearRequest;
import co.istad.lms.features.academicyear.dto.AcademicYearUpdateRequest;
import org.springframework.data.domain.Page;

public class AcademicYearServiceImpl implements AcademicYearService{
    @Override
    public void createAcademicYear(AcademicYearRequest academicYearRequest) {

    }

    @Override
    public AcademicYearDetailResponse getAcademicYearByUuid(String uuid) {
        return null;
    }

    @Override
    public Page<AcademicYearDetailResponse> getAllAcademicYears(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public AcademicYearDetailResponse updateAcademicYear(String uuid, AcademicYearUpdateRequest academicYearUpdateRequest) {
        return null;
    }

    @Override
    public void deleteAcademicYear(String academicYearUuid) {

    }

    @Override
    public void disableAcademicYearByUuid(String uuid) {

    }

    @Override
    public void enableAcademicYearByUuid(String uuid) {

    }

    @Override
    public Page<AcademicYearDetailResponse> filterAcademicYear(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {
        return null;
    }
}
