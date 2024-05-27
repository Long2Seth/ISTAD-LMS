package co.istad.lms.features.degree;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Admission;
import co.istad.lms.domain.Degree;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.mapper.DegreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeMapper degreeMapper;

    private final DegreeRepository degreeRepository;

    private final BaseSpecification<Degree> baseSpecification;

    @Override
    public void createDegree(DegreeRequest degreeRequest) {

        //validate degree by alias
        if (degreeRepository.existsByAlias(degreeRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Degree = %s has already existed.", degreeRequest.alias()));
        }

        // map DTO to entity
        Degree degree = degreeMapper.fromDegreeRequest(degreeRequest);

        //set isDeleted to false(enable)
        degree.setIsDeleted(false);

        //save to database
        degreeRepository.save(degree);

    }

    @Override
    public DegreeDetailResponse getDegreeByAlias(String alias) {

        //find degree by alias
        Degree degree = degreeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s has not been found.", alias)));

        //return degree detail
        return degreeMapper.toDegreeDetailResponse(degree);
    }


    @Override
    public Page<DegreeDetailResponse> getAllDegrees(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all degrees in database
        Page<Degree> degrees = degreeRepository.findAll(pageRequest);

        //map entity to DTO and return
        return degrees.map(degreeMapper::toDegreeDetailResponse);
    }


    @Override
    public DegreeResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest) {

        //find degree by alias
        Degree degree = degreeRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Degree = %s has not been found.", alias)));

        //check null alias from DTO
        if (degreeUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(degreeUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (degreeRepository.existsByAlias(degreeUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Degree = %s already exist.", degreeUpdateRequest.alias()));
                }
            }
        }

        //map DTO to entity
        degreeMapper.updateDegreeFromRequest(degree, degreeUpdateRequest);

        //save to database
        degreeRepository.save(degree);

        //return Degree DTO
        return degreeMapper.toDegreeResponse(degree);
    }


    @Override
    public void deleteDegreeByAlias(String alias) {

        //find degree in database by alias
        Degree degree = degreeRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Degree = %s has not been found.", alias)));

        //delete degree in database
        degreeRepository.delete(degree);
    }

    @Override
    public void enableDegreeByAlias(String alias) {

        //validate degree from dto by alias
        Degree degree = degreeRepository.findByAlias(alias)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,

                        String.format("Degree = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        degree.setIsDeleted(false);

        //save to database
        degreeRepository.save(degree);
    }

    @Override
    public void disableDegreeByAlias(String alias) {

        //validate degree from dto by alias
        Degree degree = degreeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        degree.setIsDeleted(true);

        //save to database
        degreeRepository.save(degree);

    }

    @Override
    public Page<DegreeDetailResponse> filterDegrees(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Degree entities based on the criteria provided
        Specification<Degree> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Degree> degrees = degreeRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return degrees.map(degreeMapper::toDegreeDetailResponse);

    }

}
