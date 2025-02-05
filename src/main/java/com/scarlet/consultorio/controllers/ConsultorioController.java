package com.scarlet.consultorio.controllers;

import com.scarlet.consultorio.dtos.ConsultorioRecordDto;
import com.scarlet.consultorio.models.ConsultorioModel;
import com.scarlet.consultorio.repositories.ConsultorioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.*;

@RestController
public class ConsultorioController {

    @Autowired
    ConsultorioRepository consultorioRepository;

    @PostMapping("/consultorio")
    public ResponseEntity<ConsultorioModel> saveConsultorio(@RequestBody @Valid ConsultorioRecordDto consultorioRecordDto) {
        var consultorioModel = new ConsultorioModel();
        copyProperties(consultorioRecordDto, consultorioModel);

        var savedModel = consultorioRepository.save(consultorioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
    }

    @GetMapping("/consultorio")
    public ResponseEntity<List<ConsultorioModel>> getAllConsultorio() {
        List<ConsultorioModel> consultorios = consultorioRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(consultorios);
    }

    @GetMapping("/consultorio/{id}")
    public ResponseEntity<Object> getOneConsultorio(@PathVariable(value = "id") UUID id) {
        Optional<ConsultorioModel> consultoriO = consultorioRepository.findById(id);
        if (consultoriO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultório não encontrado.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(consultoriO.get());
    }
    @PutMapping("/consultorio/{id}")
    public ResponseEntity<Object> uptadeConsultorio(@PathVariable (value="id") UUID id,
                                                    @RequestBody @Valid ConsultorioRecordDto consultorioRecordDto) {
        Optional<ConsultorioModel> consultoriO = consultorioRepository.findById(id);
        if(consultoriO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultório não encontrado.");
        }
        var consultorioModel = consultoriO.get();
        copyProperties(consultorioRecordDto, consultorioModel);
        return ResponseEntity.status(HttpStatus.OK).body(consultorioRepository.save(consultorioModel));
    }

    @DeleteMapping("/consultorio/{id}")
    public ResponseEntity<Object> deleteConsultorio(@PathVariable(value = "id") UUID id) {
        Optional<ConsultorioModel> consultoriO = consultorioRepository.findById(id);
        if(consultoriO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultório não encontrado.");
        }
        consultorioRepository.delete(consultoriO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Consultório removido com sucesso.");
    }



}

