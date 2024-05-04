package tech.noetzold.JVMParamsCompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;

public interface DataIntensiveRepository extends JpaRepository<DataIntensiveModel, Long> {
}
