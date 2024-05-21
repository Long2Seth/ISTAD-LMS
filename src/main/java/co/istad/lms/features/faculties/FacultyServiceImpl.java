package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Faculty;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import co.istad.lms.mapper.FacultyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;
    private final BaseSpecification<Faculty> baseSpecification;
    @Override
    public void createNewFaculty(FacultyRequest facultyRequest) {

        if(facultyRepository.existsByAlias(facultyRequest.alias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Faculty = %s already exists.", facultyRequest.alias()));
        }
        Faculty faculty=facultyMapper.fromFacultyRequest(facultyRequest);
        faculty.setIsDeleted(false);
        facultyRepository.save(faculty);
    }

    @Override
    public FacultyDetailResponse getFacultyByAlias(String alias) {

        Faculty faculty = facultyRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s was not found.", alias)));

        return facultyMapper.toFacultyDetailResponse(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> getAllFaculties(int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);
        Page<Faculty> faculties = facultyRepository.findAll(pageRequest);

        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }

    @Override
    public FacultyResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest) {

        Faculty faculty = facultyRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s was not found.", alias)));


        facultyMapper.updateFacultyFromRequest(faculty, facultyUpdateRequest);
        facultyRepository.save(faculty);

        return facultyMapper.toFacultyResponse(faculty);
    }

    @Override
    public void deleteFacultyByAlias(String alias) {

        Faculty faculty = facultyRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s was not found.", alias)));

        facultyRepository.delete(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> filter(BaseSpecification.FilterDto filterDto, int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        Specification<Faculty> specification = baseSpecification.filter(filterDto);

        Page<Faculty> faculties = facultyRepository.findAll(specification,pageRequest);

        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }
}
