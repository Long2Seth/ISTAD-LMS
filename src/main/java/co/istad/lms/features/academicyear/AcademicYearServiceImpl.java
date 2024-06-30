package co.istad.lms.features.academicyear;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.AcademicYear;
import co.istad.lms.domain.Admission;
import co.istad.lms.domain.Attendance;
import co.istad.lms.features.academicyear.dto.AcademicYearDetailResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearRequest;
import co.istad.lms.features.academicyear.dto.AcademicYearUpdateRequest;
import co.istad.lms.mapper.AcademicYearMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService{

    private final AcademicYearMapper academicYearMapper;

    private final AcademicYearRepository academicYearRepository;

    private final BaseSpecification<AcademicYear> baseSpecification;

    @Override
    public void createAcademicYear(AcademicYearRequest academicYearRequest) {

        if(academicYearRepository.existsByAlias(academicYearRequest.alias())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT
                    ,String.format("academicYear with alias = %s has " +
                    "already existed",academicYearRequest.alias()));
        }

        AcademicYear academicYear =academicYearMapper.fromAcademicYearRequest(academicYearRequest);

        academicYear.setIsDeleted(false);

        academicYear.setIsDraft(false);

        academicYear.setStatus(1);

        academicYearRepository.save(academicYear);

    }

    @Override
    public AcademicYearDetailResponse getAcademicYearByAlias(String alias) {

        AcademicYear academicYear=
                academicYearRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("academicYear  = %s has not been found",alias)));

        return academicYearMapper.toAcademicYearDetailResponse(academicYear);
    }

    @Override
    public Page<AcademicYearDetailResponse> getAllAcademicYears(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "alias");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all attendance in database
        Page<AcademicYear> academicYears = academicYearRepository.findAll(pageRequest);

        //map entity to DTO and return
        return academicYears.map(academicYearMapper::toAcademicYearDetailResponse);

    }

    @Override
    public AcademicYearDetailResponse updateAcademicYear(String alias,
                                                         AcademicYearUpdateRequest academicYearUpdateRequest) {

        AcademicYear academicYear=
                academicYearRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("AcademicYear  = %s has not been found",alias)));

//        if()
        return null;


    }

    @Override
    public void deleteAcademicYear(String alias) {

        AcademicYear academicYear =
                academicYearRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("AcademicYear = %s has not been found",alias)));

        academicYearRepository.delete(academicYear);

    }

    @Override
    public void disableAcademicYearByAlias(String alias) {

        AcademicYear academicYear =
                academicYearRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("AcademicYear = %s has not been found",alias)));

        academicYear.setIsDeleted(true);

        academicYearRepository.save(academicYear);
    }

    @Override
    public void enableAcademicYearByAlias(String alias) {

        AcademicYear academicYear =
                academicYearRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("AcademicYear = %s has not been found",alias)));

        academicYear.setIsDeleted(false);

        academicYearRepository.save(academicYear);
    }

    @Override
    public Page<AcademicYearDetailResponse> filterAcademicYear(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {
        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "alias");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Admission entities based on the criteria provided
        Specification<AcademicYear> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<AcademicYear> academicYearsPage = academicYearRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return academicYearsPage.map(academicYearMapper::toAcademicYearDetailResponse);
    }
}
