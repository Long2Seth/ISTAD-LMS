package co.istad.lms.features.degree;

import co.istad.lms.base.BaseSpecification;
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

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeMapper degreeMapper;
    private final DegreeRepository degreeRepository;
    private final BaseSpecification<Degree> baseSpecification;

    @Override
    public void createDegree(DegreeRequest degreeRequest) {

        if (degreeRepository.existsByAlias(degreeRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Degree = %s already exists.", degreeRequest.alias()));
        }
        if (degreeRepository.existsByLevel(degreeRequest.level())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Degree = %s already exists.", degreeRequest.level()));
        }

        Degree degree = degreeMapper.fromDegreeRequest(degreeRequest);
        degree.setIsDeleted(false);
        degreeRepository.save(degree);

    }

    @Override
    public DegreeDetailResponse getDegreeByAlias(String alias) {

        Degree degree = degreeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s was not found.", alias)));

        return degreeMapper.toDegreeDetailResponse(degree);
    }


    @Override
    public Page<DegreeDetailResponse> getAllDegrees(int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);
        Page<Degree> degrees = degreeRepository.findAll(pageRequest);

        return degrees.map(degreeMapper::toDegreeDetailResponse);
    }


    @Override
    public DegreeResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest) {

        Degree degree = degreeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s was not found.", alias)));

        degreeMapper.updateDegreeFromRequest(degree, degreeUpdateRequest);
        degreeRepository.save(degree);

        return degreeMapper.toDegreeResponse(degree);
    }



    @Override
    public void deleteDegreeByAlias(String alias) {

        Degree degree = degreeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Degree = %s was not found.", alias)));

        degreeRepository.delete(degree);
    }

    @Override
    public Page<DegreeDetailResponse> filterDegree(BaseSpecification.FilterDto filterDto, int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        Specification<Degree> specification = baseSpecification.filter(filterDto);

        Page<Degree> degrees = degreeRepository.findAll(specification,pageRequest);

        return degrees.map(degreeMapper::toDegreeDetailResponse);

    }

}
