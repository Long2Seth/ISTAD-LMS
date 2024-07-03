package co.istad.lms.features.material;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Material;
import co.istad.lms.domain.Subject;
import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.material.dto.MaterialRequest;
import co.istad.lms.features.material.dto.MaterialResponse;
import co.istad.lms.features.material.dto.MaterialUpdateRequest;
import co.istad.lms.features.media.MediaService;
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

    private final MediaService mediaService;

    @Override
    public void createMaterial(MaterialRequest materialRequest) {

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
    public MaterialDetailResponse getMaterialByUuid(String uuid) {

        // Find material by uuid
        Material material =
                materialRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , String.format("Material = %s has not been found.", uuid)));

        String url = null;
        if (material.getContentType().trim().equalsIgnoreCase("video")) {
            url = material.getFileName();
        } else {
            url = mediaService.getDownloadUrl(material.getFileName());
        }

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
            String url = null;
            if (material.getContentType().trim().equalsIgnoreCase("video")) {
                url = material.getFileName();
            } else {
                url = mediaService.getDownloadUrl(material.getFileName());
            }
            return materialMapper.toMaterialDetailResponse(material, url);
        });
    }

    @Override
    public MaterialDetailResponse updateMaterialByUuid(String uuid, MaterialUpdateRequest materialUpdateRequest) {

        // Find material by uuid
        Material material =
                materialRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Material = %s has not been found.", uuid)));


        // Map DTO to entity
        materialMapper.updateMaterialFromRequest(material, materialUpdateRequest);

        // Save to database
        materialRepository.save(material);

        // Return Material response
        String url = null;
        if (material.getContentType().trim().equalsIgnoreCase("video")) {
            url = material.getFileName();
        } else {
            url = mediaService.getDownloadUrl(material.getFileName());
        }

        return materialMapper.toMaterialDetailResponse(material, url);
    }

    @Override
    public void deleteMaterialByUuid(String uuid) {

        // Find material in database by uuid
        Material material =
                materialRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , String.format("Material = %s has not been found.", uuid)));

        // Delete material in database
        materialRepository.delete(material);
    }

    @Override
    public void enableMaterialByUuid(String uuid) {

        // Validate material from dto by uuid
        Material material =
                materialRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , String.format("Material = %s has not been found ! ", uuid)));

        // Enable material (assuming there's a field to handle enable/disable status)

        materialRepository.save(material);
    }

    @Override
    public void disableMaterialByUuid(String uuid) {

        // Validate material from dto by uuid
        Material material =
                materialRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , String.format("Material = %s has not been found ! ", uuid)));

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
            String url = null;
            if (material.getContentType().trim().equalsIgnoreCase("video")) {
                url = material.getFileName();
            } else {
                url = mediaService.getDownloadUrl(material.getFileName());
            }
            return materialMapper.toMaterialDetailResponse(material, url);
        });
    }

}
