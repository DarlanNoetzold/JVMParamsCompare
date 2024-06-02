package tech.noetzold.JVMParamsCompare.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;
import tech.noetzold.JVMParamsCompare.repository.DataIntensiveRepository;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class DataIntensiveService {

    @Autowired
    DataIntensiveRepository dataIntensiveRepository;

    @Transactional
    public DataIntensiveModel saveResult(DataIntensiveModel dataIntensiveModel){

        String result = LongStream.rangeClosed(1, dataIntensiveModel.getSize())
                .mapToObj(Long::toString)
                .collect(Collectors.joining(", "));

        dataIntensiveModel.setResult(result);

        return dataIntensiveRepository.save(dataIntensiveModel);
    }
}
