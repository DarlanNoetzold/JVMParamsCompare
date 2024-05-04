package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;
import tech.noetzold.JVMParamsCompare.service.HeavyCalculationService;

@RestController
@RequestMapping("/heavy-calculation")
public class HeavyCalculationController {

    @Autowired
    HeavyCalculationService heavyCalculationService;

    @GetMapping("/compute/{max}")
    public ResponseEntity<HeavyCalculationModel> computeHeavyCalculations(@PathVariable long max) {

        double result = 0;
        for (int i = 0; i < max; i++) {
            result += Math.sin(i) * Math.tan(i);
        }

        HeavyCalculationModel heavyCalculationModel = heavyCalculationService
                .saveResult(new HeavyCalculationModel(0L, max, result));

        return ResponseEntity.ok(heavyCalculationModel);
    }
}

