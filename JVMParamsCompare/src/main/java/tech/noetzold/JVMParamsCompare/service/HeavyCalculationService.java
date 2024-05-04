package tech.noetzold.JVMParamsCompare.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;
import tech.noetzold.JVMParamsCompare.repository.HeavyCalculationRepository;

@Service
public class HeavyCalculationService {

    @Autowired
    HeavyCalculationRepository heavyCalculationRepository;

    @Transactional
    public HeavyCalculationModel saveResult(HeavyCalculationModel heavyCalculationModel){
        return heavyCalculationRepository.save(heavyCalculationModel);
    }
}
