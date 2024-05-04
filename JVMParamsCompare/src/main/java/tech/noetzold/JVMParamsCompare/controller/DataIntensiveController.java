package tech.noetzold.JVMParamsCompare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;
import tech.noetzold.JVMParamsCompare.service.DataIntensiveService;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/data-intensive")
public class DataIntensiveController {

    @Autowired
    DataIntensiveService dataIntensiveService;

    @GetMapping("/process")
    public ResponseEntity<DataIntensiveModel> processDataIntensively(@PathVariable Long size) {
        String result = LongStream.rangeClosed(1, size)
                .mapToObj(Long::toString)
                .collect(Collectors.joining(", "));

        DataIntensiveModel dataIntensiveModel = dataIntensiveService.saveResult(new DataIntensiveModel(0L, size, result));

        return ResponseEntity.ok(dataIntensiveModel);
    }
}

