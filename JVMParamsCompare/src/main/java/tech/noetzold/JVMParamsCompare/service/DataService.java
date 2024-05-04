package tech.noetzold.JVMParamsCompare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.noetzold.JVMParamsCompare.model.DataModel;
import tech.noetzold.JVMParamsCompare.repository.DataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Transactional
    public DataModel addData(DataModel data) {
        return dataRepository.save(data);
    }

    @Transactional(readOnly = true)
    public List<DataModel> getAllData() {
        return dataRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DataModel getDataById(Long id) {
        Optional<DataModel> data = dataRepository.findById(id);
        return data.orElseThrow(() -> new RuntimeException("Data not found with id: " + id));
    }

    @Transactional
    public DataModel updateData(Long id, DataModel newData) {
        DataModel data = getDataById(id);
        data.setDate(newData.getDate());
        data.setFile(newData.getFile());
        data.setList(newData.getList());
        data.setName(newData.getName());
        data.setLongText(newData.getLongText());
        data.setNumber(newData.getNumber());

        return dataRepository.save(data);
    }

    @Transactional
    public void deleteData(Long id) {
        dataRepository.deleteById(id);
    }
}