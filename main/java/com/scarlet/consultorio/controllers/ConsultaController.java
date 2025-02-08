package com.scarlet.consultorio.controllers;

import com.scarlet.consultorio.dtos.ConsultaRecordDto;
import com.scarlet.consultorio.models.ConsultaModel;
import com.scarlet.consultorio.repositories.ConsultaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
public class ConsultaController {
    @Autowired
    ConsultaRepository consultaRepository;

    @PostMapping("/consulta")
    public ResponseEntity<ConsultaModel> saveConsulta(@RequestBody ConsultaRecordDto consultaRecordDto) {
        var consultaModel = new ConsultaModel();
        BeanUtils.copyProperties (consultaRecordDto, consultaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaRepository.save(consultaModel));

    }

    @GetMapping("/consulta")
    public ResponseEntity<List<ConsultaModel>> getAllConsulta() {
        List<ConsultaModel> consultaModelList = consultaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAll());
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<Object> getOneConsulta(@PathVariable(value = "id") UUID id) {
        Optional<ConsultaModel> consultaModel = consultaRepository.findById(id);
        if (consultaModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não localizada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(consultaModel.get());
    }

    @PutMapping("/consulta/{id}")
    public ResponseEntity<Object> updateConsulta(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid ConsultaRecordDto consultaRecordDto) {
        Optional<ConsultaModel> consulta = consultaRepository.findById(id);
        if (consulta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }
        var consultaModel = consulta.get();
        copyProperties(consultaRecordDto, consultaModel);
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.save(consultaModel));
    }

    @DeleteMapping("/consulta/{id}")
    public ResponseEntity<Object> deleteConsulta(@PathVariable(value = "id") UUID id) {
        Optional<ConsultaModel> consulta = consultaRepository.findById(id);
        if (consulta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
        }
        consultaRepository.delete(consulta.get());
        return ResponseEntity.status(HttpStatus.OK).body("Consulta removida com sucesso.");
    }

}

