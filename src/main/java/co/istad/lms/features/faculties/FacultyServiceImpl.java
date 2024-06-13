package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Faculty;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import co.istad.lms.features.file.FileMetaDataRepository;
import co.istad.lms.features.media.MediaService;
import co.istad.lms.features.minio.MinioStorageService;
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
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private final FacultyMapper facultyMapper;

    private final MinioStorageService minioStorageService;

    private final BaseSpecification<Faculty> baseSpecification;

    private final FileMetaDataRepository fileMetaDataRepository;

    private final MediaService mediaService;

    @Override
    public void createFaculty(FacultyRequest facultyRequest) {

        //validate faculty from DTO by alias
        if (facultyRepository.existsByAlias(facultyRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Faculty = %s already exists.", facultyRequest.alias()));
        }

        //validate logo is available or not
        if (facultyRequest.logo() != null && !facultyRequest.logo().trim().isEmpty() && !fileMetaDataRepository.existsByFileName(facultyRequest.logo())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Logo = %s has not been found",
                    facultyRequest.logo()));
        }

        //map from DTO to entity
        Faculty faculty = facultyMapper.fromFacultyRequest(facultyRequest);

        //set it to enable
        faculty.setIsDeleted(false);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public FacultyDetailResponse getFacultyByAlias(String alias) {


        //find faculty in database by alias
        Faculty faculty = facultyRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found.", alias)));

        //set logo url to faculty
        if (faculty.getLogo() != null && !faculty.getLogo().trim().isEmpty()) {
            faculty.setLogo(mediaService.getUrl((faculty.getLogo())));
        }

        //return Faculty detail
        return facultyMapper.toFacultyDetailResponse(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> getAllFaculties(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //get all faculty from database
        Page<Faculty> faculties = facultyRepository.findAll(pageRequest);

        // update the logo URL for each faculty
        faculties.forEach(faculty -> {
            if (faculty.getLogo() != null && !faculty.getLogo().trim().isEmpty()) {
                faculty.setLogo(mediaService.getUrl(faculty.getLogo()));
            }
        });

        //map entity to DTO and return
        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }

    @Override
    public FacultyDetailResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest) {

        //validate faculty from DTO with alias
        Faculty faculty = facultyRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found.", alias)));

        //check null alias from DTO
        if (facultyUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(facultyUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (facultyRepository.existsByAlias(facultyUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Faculty = %s already exist.", facultyUpdateRequest.alias()));
                }
            }
        }


        //map from DTO to entity
        facultyMapper.updateFacultyFromRequest(faculty, facultyUpdateRequest);


        if (facultyUpdateRequest.logo() != null && !facultyUpdateRequest.logo().trim().isEmpty()) {

            //validate logo is available or not
            if (!fileMetaDataRepository.existsByFileName(facultyUpdateRequest.logo())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Logo = %s has not been found",
                        facultyUpdateRequest.logo()));
            }
        }

        //save to database
        facultyRepository.save(faculty);

        //set logo to faculty
        faculty.setLogo(mediaService.getUrl(faculty.getLogo()));

        //return faculty detail
        return facultyMapper.toFacultyDetailResponse(faculty);
    }

    @Override
    public void deleteFacultyByAlias(String alias) {

        //validate faculty from DTO with alias
        Faculty faculty = facultyRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found.", alias)));

        //save to database
        facultyRepository.delete(faculty);
    }

    @Override
    public void disableFacultyByAlias(String facultyAlias) {

        //validate from dto with alias
        Faculty faculty = facultyRepository.findByAlias(facultyAlias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found ! ", facultyAlias)));

        //set isDelete to true (disable)
        faculty.setIsDeleted(true);

        //set isDraft to false(public)
        faculty.setIsDraft(true);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public void enableFacultyByAlias(String facultyAlias) {

        //validate from dto by alias
        Faculty faculty = facultyRepository.findByAlias(facultyAlias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found ! ", facultyAlias)));

        //set isDeleted to false(enable)
        faculty.setIsDeleted(false);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public void publicFacultyByAlias(String alias) {

        //validate from dto by alias
        Faculty faculty =
                facultyRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found ! ", alias)));

        //set isDraft to false(public)
        faculty.setIsDraft(false);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public void draftFacultyByAlias(String alias) {


        //validate from dto by alias
        Faculty faculty =
                facultyRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found ! ", alias)));

        //set isDraft to false(private)
        faculty.setIsDraft(true);

        //save to database
        facultyRepository.save(faculty);
    }

    @Override
    public Page<FacultyDetailResponse> filterFaculties(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Faculty entities based on the criteria provided
        Specification<Faculty> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Faculty> faculties = facultyRepository.findAll(specification, pageRequest);

        //set logo url to faculty
        faculties.forEach(faculty -> {
            if (faculty.getLogo() != null && !faculty.getLogo().trim().isEmpty()) {
                faculty.setLogo(mediaService.getUrl(faculty.getLogo()));
            }
        });

        //map to DTO and return
        return faculties.map(facultyMapper::toFacultyDetailResponse);
    }
}
