package tech.noetzold.JVMParamsCompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;

@Repository
public interface HeavyCalculationRepository extends JpaRepository<HeavyCalculationModel, Long> {
}
