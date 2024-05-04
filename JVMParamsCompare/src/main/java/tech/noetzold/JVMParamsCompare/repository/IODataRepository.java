package tech.noetzold.JVMParamsCompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.noetzold.JVMParamsCompare.model.IODataModel;

@Repository
public interface IODataRepository extends JpaRepository<IODataModel, Long> {
}
