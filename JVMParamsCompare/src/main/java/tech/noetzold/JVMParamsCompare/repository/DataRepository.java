package tech.noetzold.JVMParamsCompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.noetzold.JVMParamsCompare.model.DataModel;

@Repository
public interface DataRepository extends JpaRepository<DataModel, Long> {
}