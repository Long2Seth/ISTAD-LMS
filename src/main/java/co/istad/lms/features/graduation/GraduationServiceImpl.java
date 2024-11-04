package co.istad.lms.features.graduation;


import co.istad.lms.domain.Graduation;
import co.istad.lms.features.graduation.dto.GraduationRequest;
import co.istad.lms.features.graduation.dto.GraduationResponse;
import co.istad.lms.mapper.GraduationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GraduationServiceImpl implements GraduationService {

    private final GraduationRepository graduationRepository;
    private final GraduationMapper graduationMapper;


    @Override
    public Page<GraduationResponse> findAll(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "gpa"));

        List<Graduation> graduations = graduationRepository.findAll(pageRequest).toList();

        return new PageImpl<>(graduations, pageRequest, graduations.size()).
                map(graduationMapper::toResponse);
    }




    @Override
    public GraduationResponse findById(String uuid) {

        Graduation graduation = graduationRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Graduation = %s has not been found.", uuid)
                        )
                );
        return graduationMapper.toResponse(graduation);
    }



    @Override
    public GraduationResponse createGraduation(GraduationRequest graduationRequest) {

        if(graduationRepository.existsByGpa(graduationRequest.gpa())){

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Graduation = %s already exists.", graduationRequest.gpa()));
        }

        Graduation graduation = graduationMapper.toRequest(graduationRequest);
        graduation.setUuid(UUID.randomUUID().toString());

        return graduationMapper.toResponse(graduationRepository.save(graduation));
    }



    @Override
    public GraduationResponse updateGraduation(String uuid, GraduationRequest graduationRequest) {

        Graduation graduation = graduationRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Graduation = %s has not been found.", uuid)
                        )
                );

        graduation.setGpa(graduationRequest.gpa());
        graduation.setGrade(graduationRequest.grade());
        graduation.setScore(graduationRequest.score());

        return graduationMapper.toResponse(graduationRepository.save(graduation));

    }



    @Override
    public void deleteGraduation(String uuid) {

        Graduation graduation = graduationRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Graduation = %s has not been found.", uuid)
                        )
                );

        graduationRepository.delete(graduation);

    }



}
