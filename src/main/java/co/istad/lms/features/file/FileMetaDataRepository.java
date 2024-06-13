package co.istad.lms.features.file;

import co.istad.lms.domain.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FileMetaDataRepository extends JpaRepository<FileMetaData,Long>, JpaSpecificationExecutor<FileMetaData> {

    Optional<FileMetaData> findByFileName(String fileName);

    boolean existsByFileName(String fileName);
}
