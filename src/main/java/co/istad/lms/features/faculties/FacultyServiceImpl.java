package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Admission;
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
    public void createFaculty(FacultyRequest facultyRequest) {

        //validate faculty from DTO by alias
        if(facultyRepository.existsByAlias(facultyRequest.alias())){

            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Faculty = %s already exists.", facultyRequest.alias()));
        }

        //map from DTO to entity
        Faculty faculty=facultyMapper.fromFacultyRequest(facultyRequest);

        //set it to enable
        faculty.setIsDeleted(false);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public FacultyDetailResponse getFacultyByAlias(String alias) {


        //find faculty in database by alias
        Faculty faculty = facultyRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s has not been found.", alias)));


        //return Faculty detail
        return facultyMapper.toFacultyDetailResponse(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> getAllFaculties(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination

        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //get all faculty from database
        Page<Faculty> faculties = facultyRepository.findAll(pageRequest);

        //map entity to DTO and return
        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }

    @Override
    public FacultyDetailResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest) {

        //validate faculty from DTO with alias
        Faculty faculty = facultyRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s has not been found.", alias)));

        //check null alias from DTO
        if(facultyUpdateRequest.alias()!=null){

            //validate alias from dto with original alias
            if(!alias.equalsIgnoreCase(facultyUpdateRequest.alias())){

                //validate new alias is conflict with other alias or not
                if(facultyRepository.existsByAlias(facultyUpdateRequest.alias())){

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Faculty = %s already exist.", facultyUpdateRequest.alias()));
                }
            }
        }


        //map from DTO to entity
        facultyMapper.updateFacultyFromRequest(faculty, facultyUpdateRequest);

        //save to database
        facultyRepository.save(faculty);

        //return faculty detail
        return facultyMapper.toFacultyDetailResponse(faculty);
    }

    @Override
    public void deleteFacultyByAlias(String alias) {

        //validate faculty from DTO with alias
        Faculty faculty = facultyRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s has not been found.", alias)));

        //save to database
        facultyRepository.delete(faculty);
    }

    @Override
    public void disableFacultyByAlias(String facultyAlias) {

        //validate from dto with alias
        Faculty faculty = facultyRepository.findByAlias(facultyAlias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s has not been found ! ", facultyAlias)));

        //set isDelete to true (disable)
        faculty.setIsDeleted(true);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public void enableFacultyByAlias(String facultyAlias) {

        //validate from dto by alias
        Faculty faculty = facultyRepository.findByAlias(facultyAlias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Faculty = %s has not been found ! ", facultyAlias)));

        //set isDeleted to false(enable)
        faculty.setIsDeleted(false);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> filterFaculties(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Faculty entities based on the criteria provided
        Specification<Faculty> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Faculty> faculties = facultyRepository.findAll(specification,pageRequest);

        //map to DTO and return
        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }
}
