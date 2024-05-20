package co.istad.lms.features.degree;

import co.istad.lms.domain.Degree;
import co.istad.lms.features.degree.dto.DegreeCreateRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.mapper.DegreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeMapper degreeMapper;
    private final DegreeRepository degreeRepository;


    @Override
    public DegreeDetailResponse createDegree(DegreeCreateRequest degreeRequest) {

        if(degreeRepository.existsByAlias(degreeRequest.alias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "degree with alias "+degreeRequest.alias()+"is already exist!!");
        }

        if(degreeRepository.existsByLevel(degreeRequest.alias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "degree with alias "+degreeRequest.alias()+"is already exist!!");
        }

        Degree degree =degreeMapper.fromDegreeRequest(degreeRequest);
        return null;
    }

    @Override
    public DegreeDetailResponse getDegreeByAlias(String alias) {
        return null;
    }

    @Override
    public DegreeDetailResponse getDegreeByLevel(String level) {
        return null;
    }

    @Override
    public DegreeDetailResponse getAllDegree() {
        return null;
    }

    @Override
    public DegreeResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest) {
        return null;
    }

    @Override
    public DegreeResponse updateDegreeByLevel(String level, DegreeUpdateRequest degreeUpdateRequest) {
        return null;
    }

    @Override
    public void deleteDegreeByAlias(String alias) {

    }

    @Override
    public void deleteDegreeByLevel(String level) {

    }
}
