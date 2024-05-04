package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;

@RestController
@RequestMapping("/io-simulation")
public class IOSimulationController {

    @GetMapping("/simulate")
    public String simulateIOOperations() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Linha " + i + "\n");
            }
        }

        // Lendo o arquivo
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // Deletando o arquivo temporário
        tempFile.delete();

        return "Operações de I/O simuladas com sucesso.";
    }
}