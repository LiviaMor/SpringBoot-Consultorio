package com.scarlet.consultorio.controllers;

import com.scarlet.consultorio.dtos.AnamneseRecordDto;
import com.scarlet.consultorio.models.AnamneseModel;
import com.scarlet.consultorio.repositories.AnamneseRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
public class AnamneseController {
    @Autowired
    AnamneseRepository anamneseRepository;

    @PostMapping("/anamnese")
    public ResponseEntity<AnamneseModel> saveAnamnese(@Valid @RequestBody AnamneseRecordDto anamneseRecordDto) {
        var anamneseModel = new AnamneseModel();
        copyProperties(anamneseRecordDto, anamneseModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(anamneseRepository.save(anamneseModel));
    }

    @GetMapping("/anamnese")
    public ResponseEntity<List<AnamneseModel>> getAllAnamnese() {
        List<AnamneseModel> anamneseModelList = anamneseRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(anamneseModelList);
    }

    @GetMapping("/anamnese/{id}")
    public ResponseEntity<Object> getOneAnamnese(@PathVariable(value = "id") UUID id) {
        Optional<AnamneseModel> anamneseModel = anamneseRepository.findById(id);
        if (anamneseModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anamnese não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(anamneseModel.get());
    }

    @PutMapping("/anamnese/{id}")
    public ResponseEntity<Object> updateAnamnese(@PathVariable(value = "id") UUID id,
                                                 @RequestBody AnamneseRecordDto anamneseRecordDto) {
        Optional<AnamneseModel> anamneseOptional = anamneseRepository.findById(id);
        if (anamneseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anamnese não encontrada.");
        }
        var anamneseModel = anamneseOptional.get();
        copyProperties(anamneseRecordDto, anamneseModel);
        return ResponseEntity.status(HttpStatus.OK).body(anamneseRepository.save(anamneseModel));
    }

    @DeleteMapping("/anamnese/{id}")
    public ResponseEntity<Object> deleteAnamnese(@PathVariable(value = "id") UUID id) {
        Optional<AnamneseModel> anamneseModel = anamneseRepository.findById(id);
        if (anamneseModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anamnese não encontrada.");
        }
        anamneseRepository.delete(anamneseModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Anamnese removida com sucesso.");
    }
}
