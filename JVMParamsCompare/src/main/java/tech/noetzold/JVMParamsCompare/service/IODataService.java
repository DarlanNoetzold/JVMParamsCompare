package tech.noetzold.JVMParamsCompare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.noetzold.JVMParamsCompare.model.IODataModel;
import tech.noetzold.JVMParamsCompare.repository.IODataRepository;

@Service
public class IODataService {

    @Autowired
    private IODataRepository ioDataRepository;

    @Transactional
    public IODataModel saveData(String data) {
        IODataModel ioData = new IODataModel(data);
        return ioDataRepository.save(ioData);
    }
}
