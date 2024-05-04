package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.model.IODataModel;
import tech.noetzold.JVMParamsCompare.service.IODataService;

import java.io.*;

@RestController
@RequestMapping("/io-simulation")
public class IOSimulationController {

    @Autowired
    private IODataService ioDataService;

    @GetMapping("/simulate")
    public ResponseEntity<IODataModel> simulateIOOperations() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Linha " + i + "\n");
            }
        }

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        tempFile.delete();
        IODataModel ioDataModel = ioDataService.saveData(output.toString());


        return ResponseEntity.ok(ioDataModel);
    }
}