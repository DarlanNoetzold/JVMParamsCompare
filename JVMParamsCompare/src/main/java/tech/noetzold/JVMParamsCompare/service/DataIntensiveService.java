package tech.noetzold.JVMParamsCompare.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;
import tech.noetzold.JVMParamsCompare.repository.DataIntensiveRepository;

@Service
public class DataIntensiveService {

    @Autowired
    DataIntensiveRepository dataIntensiveRepository;

    @Transactional
    public DataIntensiveModel saveResult(DataIntensiveModel dataIntensiveModel){
        return dataIntensiveRepository.save(dataIntensiveModel);
    }
}
