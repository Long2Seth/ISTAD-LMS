package co.istad.lms.features.material;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Material;
import co.istad.lms.domain.Subject;
import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.material.dto.MaterialRequest;
import co.istad.lms.features.material.dto.MaterialResponse;
import co.istad.lms.features.material.dto.MaterialUpdateRequest;
import co.istad.lms.features.minio.MinioStorageService;
import co.istad.lms.features.subject.SubjectRepository;
import co.istad.lms.mapper.MaterialMapper;
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
public class MaterialServiceImpl implements MaterialService {

    private final MaterialMapper materialMapper;

    private final MaterialRepository materialRepository;

    private final SubjectRepository subjectRepository;

    private final MinioStorageService minioStorageService;

    private final BaseSpecification<Material> baseSpecification;

    @Override
    public void createMaterial(MaterialRequest materialRequest) {

        // Validate material by alias
        if (materialRepository.existsByAlias(materialRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Material = %s already exists.", materialRequest.alias()));
        }

        //validate subject by alias
        Subject subject = subjectRepository.findByAlias(materialRequest.subjectAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found", materialRequest.subjectAlias())));

        // Map DTO to entity
        Material material = materialMapper.fromMaterialRequest(materialRequest);

        //set isDelete to false
        material.setIsDeleted(false);

        //set subject to material
        material.setSubject(subject);
        // Save to database
        materialRepository.save(material);
    }

    @Override
    public MaterialDetailResponse getMaterialByAlias(String alias) {

        // Find material by alias
        Material material = materialRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Material = %s has not been found.", alias)));

        String url = minioStorageService.getUrl(material.getFileName());

        // Return material detail
        return materialMapper.toMaterialDetailResponse(material, url);
    }


    @Override
    public Page<MaterialDetailResponse> getAllMaterials(int pageNumber, int pageSize) {

        // Create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        // Create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        // Find all materials in database
        Page<Material> materials = materialRepository.findAll(pageRequest);


        // Map entity to DTO and return
        return materials.map(material -> {
            String url = minioStorageService.getUrl(material.getFileName());
            return materialMapper.toMaterialDetailResponse(material, url);
        });
    }

    @Override
    public MaterialDetailResponse updateMaterialByAlias(String alias, MaterialUpdateRequest materialUpdateRequest) {

        // Find material by alias
        Material material = materialRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Material = %s has not been found.", alias)));

        // Check null alias from DTO
        if (materialUpdateRequest.alias() != null) {

            // Validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(materialUpdateRequest.alias())) {

                // Validate new alias is conflict with other alias or not
                if (materialRepository.existsByAlias(materialUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Material = %s already exist.", materialUpdateRequest.alias()));
                }
            }
        }

        // Map DTO to entity
        materialMapper.updateMaterialFromRequest(material, materialUpdateRequest);

        // Save to database
        materialRepository.save(material);

        // Return Material response
        String url = minioStorageService.getUrl(material.getFileName());
        return materialMapper.toMaterialDetailResponse(material, url);
    }

    @Override
    public void deleteMaterialByAlias(String alias) {

        // Find material in database by alias
        Material material = materialRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Material = %s has not been found.", alias)));

        // Delete material in database
        materialRepository.delete(material);
    }

    @Override
    public void enableMaterialByAlias(String alias) {

        // Validate material from dto by alias
        Material material = materialRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Material = %s has not been found ! ", alias)));

        // Enable material (assuming there's a field to handle enable/disable status)

        materialRepository.save(material);
    }

    @Override
    public void disableMaterialByAlias(String alias) {

        // Validate material from dto by alias
        Material material = materialRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Material = %s has not been found ! ", alias)));

        // Disable material (assuming there's a field to handle enable/disable status)

        materialRepository.save(material);
    }

    @Override
    public Page<MaterialDetailResponse> filterMaterials(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        // Create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        // Create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        // Create a dynamic query specification for filtering Material entities based on the criteria provided
        Specification<Material> specification = baseSpecification.filter(filterDto);

        // Get all entities that match with filter condition
        Page<Material> materials = materialRepository.findAll(specification, pageRequest);

        // Map to DTO and return
        return materials.map(material -> {
            String url = minioStorageService.getUrl(material.getFileName());
            return materialMapper.toMaterialDetailResponse(material, url);
        });
    }

}
