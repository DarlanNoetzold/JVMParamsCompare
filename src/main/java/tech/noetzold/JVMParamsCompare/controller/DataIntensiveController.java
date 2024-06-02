package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.message.config.RabbitmqQueues;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;
import tech.noetzold.JVMParamsCompare.service.DataIntensiveService;
import tech.noetzold.JVMParamsCompare.service.RabbitmqService;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/data-intensive")
public class DataIntensiveController {

    @Autowired
    DataIntensiveService dataIntensiveService;

    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping("/process/{size}")
    public ResponseEntity<DataIntensiveModel> processDataIntensively(@PathVariable Long size) {
        rabbitmqService.sendMessage(RabbitmqQueues.DATA_INTESIVE_QUEUE, new DataIntensiveModel(0L, size, ""));

        return ResponseEntity.ok(new DataIntensiveModel(0L, size, ""));
    }
}

