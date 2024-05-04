package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.JVMParamsCompare.model.DataModel;
import tech.noetzold.JVMParamsCompare.service.DataService;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping
    public ResponseEntity<DataModel> addData(@RequestBody DataModel data) {
        DataModel newData = dataService.addData(data);
        return ResponseEntity.ok(newData);
    }

    @GetMapping
    public ResponseEntity<List<DataModel>> getAllData() {
        List<DataModel> dataList = dataService.getAllData();
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataModel> getDataById(@PathVariable Long id) {
        DataModel data = dataService.getDataById(id);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataModel> updateData(@PathVariable Long id, @RequestBody DataModel newData) {
        DataModel updatedData = dataService.updateData(id, newData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        dataService.deleteData(id);
        return ResponseEntity.ok().build();
    }
}
