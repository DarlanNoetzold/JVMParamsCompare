package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.message.config.RabbitmqQueues;
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;
import tech.noetzold.JVMParamsCompare.service.HeavyCalculationService;
import tech.noetzold.JVMParamsCompare.service.RabbitmqService;

@RestController
@RequestMapping("/heavy-calculation")
public class HeavyCalculationController {

    @Autowired
    HeavyCalculationService heavyCalculationService;

    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping("/compute/{max}")
    public ResponseEntity<HeavyCalculationModel> computeHeavyCalculations(@PathVariable Long max) {

        rabbitmqService.sendMessage(RabbitmqQueues.HEAVY_CALCULATION_QUEUE, new HeavyCalculationModel(0L, max, 0L));

        return ResponseEntity.ok(new HeavyCalculationModel(0L, max, 0L));
    }
}

