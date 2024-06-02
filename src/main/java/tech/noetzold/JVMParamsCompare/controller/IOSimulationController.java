package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.message.config.RabbitmqQueues;
import tech.noetzold.JVMParamsCompare.model.IODataModel;
import tech.noetzold.JVMParamsCompare.service.IODataService;
import tech.noetzold.JVMParamsCompare.service.RabbitmqService;

import java.io.*;

@RestController
    @RequestMapping("/io-simulation")
public class IOSimulationController {

    @Autowired
    private IODataService ioDataService;

    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping("/simulate/{lines}")
    public ResponseEntity<IODataModel> simulateIOOperations(@PathVariable Long lines) throws IOException {
        IODataModel ioDataModel = new IODataModel(0L, "", lines);
        rabbitmqService.sendMessage(RabbitmqQueues.IO_SIMULATION_QUEUE, ioDataModel);

        return ResponseEntity.ok(ioDataModel);
    }
}