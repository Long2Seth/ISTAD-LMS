package co.istad.lms.features.generation;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService{

    private final GenerationRepository generationRepository;

    private final GenerationMapper generationMapper;

    private final BaseSpecification<Generation> baseSpecification;

    @Override
    public void createGeneration(GenerationRequest generationRequest) {

        //validate generation from DTO by alias
        if(generationRepository.existsByAlias(generationRequest.alias())){

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Generation = %s already exists.", generationRequest.alias()));
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

        Generation generation=generationRepository.findByAlias(alias).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found.", alias)));
        //return generation detail
        return generationMapper.toGenerationDetailResponse(generation);
    }

    @Override
    public Page<GenerationDetailResponse> getAllGenerations(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //get all generation from database
        Page<Generation> generations = generationRepository.findAll(pageRequest);

        //map entity to DTO and return
        return generations.map(generationMapper::toGenerationDetailResponse);
    }

    @Override
    public GenerationDetailResponse updateGenerationByAlias(String alias, GenerationUpdateRequest generationUpdateRequest) {

        //validate generation from DTO with alias
        Generation generation = generationRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found.", alias)));

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

        //map from DTO to entity
        generationMapper.updateGenerationFromRequest(generation,generationUpdateRequest);

        //save to database
        generationRepository.save(generation);

        //return generation to DTO
        return generationMapper.toGenerationDetailResponse(generation);

    }

    @Override
    public void deleteGenerationByAlias(String alias) {

        //find generation in database by alias
        Generation generation = generationRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found.", alias)));

        //delete generation in database
        generationRepository.delete(generation);
    }

    @Override
    public void disableGenerationByAlias(String alias) {

        //validate generation from dto by alias
        Generation generation = generationRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        generation.setIsDeleted(true);

        //save to database
        generationRepository.save(generation);

    }

    @Override
    public void enableGenerationByAlias(String alias) {

        //validate generation from dto by alias
        Generation generation = generationRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Generation = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        generation.setIsDeleted(false);

        //save to database
        generationRepository.save(generation);
    }

    @Override
    public Page<GenerationDetailResponse> filterGenerations(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Generation entities based on the criteria provided
        Specification<Generation> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Generation> generations = generationRepository.findAll(specification,pageRequest);

        //map to DTO and return
        return generations.map(generationMapper::toGenerationDetailResponse);


    }
}
