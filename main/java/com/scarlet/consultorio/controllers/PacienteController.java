package com.scarlet.consultorio.controllers;

import com.scarlet.consultorio.dtos.PacienteRecordDto;
import com.scarlet.consultorio.models.PacienteModel;
import com.scarlet.consultorio.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PacienteController {
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("/paciente")
    public ResponseEntity<PacienteModel> savePaciente(@RequestBody @Valid PacienteRecordDto pacienteRecordDto) {
        var pacienteModel = new PacienteModel();
        BeanUtils.copyProperties(pacienteRecordDto, pacienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRepository.save(pacienteModel));
    }

    @GetMapping("/paciente")
    public ResponseEntity<List<PacienteModel>> getAllPaciente() {
        List<PacienteModel> pacienteModelList = pacienteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pacienteModelList);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<PacienteModel> getPacienteById(@PathVariable UUID id) {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid PacienteRecordDto pacienteRecordDto) {
        Optional<PacienteModel> pacienteOptional = pacienteRepository.findById(id);
        if (!pacienteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
        }

        PacienteModel pacienteModel = pacienteOptional.get();
        pacienteModel.setNomePaciente(pacienteRecordDto.nomePaciente());
        pacienteModel.setCpfPaciente(pacienteRecordDto.cpfPaciente());
        pacienteModel.setRgPaciente(pacienteRecordDto.rgPaciente());
        pacienteModel.setEnderecoPaciente(pacienteRecordDto.enderecoPaciente());
        pacienteModel.setTelefonePaciente(pacienteRecordDto.telefonePaciente());
        pacienteModel.setEmailPaciente(pacienteRecordDto.emailPaciente());
        pacienteModel.setDataNascimentoPaciente(pacienteRecordDto.dataNascimentoPaciente());
        pacienteModel.setSexoPaciente(pacienteRecordDto.sexoPaciente());

        pacienteModel.setIdPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.save(pacienteModel));
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable(value = "id") UUID id) {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");

        }
        pacienteRepository.delete(paciente.get());
        return ResponseEntity.status(HttpStatus.OK).body("Paciente removido com sucesso.");
    }

}
