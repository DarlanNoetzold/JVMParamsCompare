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

        double result = 0;
        for (int i = 0; i < heavyCalculationModel.getMax(); i++) {
            result += Math.sin(i) * Math.tan(i);
        }

        heavyCalculationModel.setResult(result);

        return heavyCalculationRepository.save(heavyCalculationModel);
    }
}
