package co.istad.lms.features.generation;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Faculty;
import co.istad.lms.domain.Generation;
import co.istad.lms.features.generation.dto.GenerationDetailResponse;
import co.istad.lms.features.generation.dto.GenerationRequest;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.generation.dto.GenerationUpdateRequest;
import co.istad.lms.mapper.GenerationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService{

    private final GenerationRepository generationRepository;
    private final GenerationMapper generationMapper;

    @Override
    public void createGeneration(GenerationRequest generationRequest) {

        //validate generation from DTO by alias
        if(generationRepository.existsByAlias(generationRequest.alias())){

            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Generation = %s already exists.", generationRequest.alias()));
        }

        //map from DTO to entity
        Generation generation=generationMapper.fromGenerationRequest(generationRequest);

        //set isDeleted to false(enable)
        generation.setIsDeleted(false);

        //save to database
        generationRepository.save(generation);
    }

    @Override
    public GenerationDetailResponse getGenerationByAlias(String alias) {

        Generation generation=generationRepository.findByAlias(alias)

                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Generation = %s has not been found.", alias)));
        //return generation detail
        return generationMapper.toGenerationDetailResponse(generation);
    }

    @Override
    public Page<GenerationDetailResponse> getAllGeneration(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination

        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //get all generation from database
        Page<Generation> generations = generationRepository.findAll(pageRequest);

        //map entity to DTO and return
        return generations.map(generationMapper::toGenerationDetailResponse);
    }

    @Override
    public GenerationResponse updateGenerationByAlias(String alias, GenerationUpdateRequest generationUpdateRequest) {

        //validate generation from DTO with alias
        Generation generation = generationRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Generation = %s has not been found.", alias)));

        //check null alias from DTO
        if(generationUpdateRequest.alias()!=null){

            //validate alias from dto with original alias
            if(!alias.equalsIgnoreCase(generationUpdateRequest.alias())){

                //validate new alias is conflict with other alias or not
                if(generationRepository.existsByAlias(generationUpdateRequest.alias())){

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Faculty = %s already exist.", generationUpdateRequest.alias()));
                }
            }
        }
        return null;
    }

    @Override
    public void deleteGenerationByAlias(String alias) {

    }

    @Override
    public void disableGenerationByAlias(String alias) {

    }

    @Override
    public void enableGenerationByAlias(String alias) {

    }

    @Override
    public Page<GenerationDetailResponse> filterGenerations(BaseSpecification.FilterDto filterDto, int page, int size) {
        return null;
    }
}
