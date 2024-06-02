package tech.noetzold.JVMParamsCompare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.noetzold.JVMParamsCompare.model.IODataModel;
import tech.noetzold.JVMParamsCompare.repository.IODataRepository;

import java.io.*;

@Service
public class IODataService {

    @Autowired
    private IODataRepository ioDataRepository;

    @Transactional
    public IODataModel saveData(IODataModel ioDataModel) throws IOException {

        File tempFile = File.createTempFile("test", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (int i = 0; i < ioDataModel.getLines(); i++) {
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

        ioDataModel.setData(output.toString());

        tempFile.delete();
        return ioDataRepository.save(ioDataModel);
    }
}
