package com.scarlet.consultorio.controllers;


import com.scarlet.consultorio.dtos.CaixaRecordDto;
import com.scarlet.consultorio.models.CaixaModel;
import com.scarlet.consultorio.repositories.CaixaRepository;
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
public class CaixaController {

    @Autowired
    CaixaRepository caixaRepository;

    @PostMapping("/caixa")
    public ResponseEntity<CaixaModel> saveCaixa(@RequestBody @Valid CaixaRecordDto caixaRecordDto) {
        var caixaModel = new CaixaModel();
        BeanUtils.copyProperties(caixaRecordDto, caixaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(caixaRepository.save(caixaModel));
    }

    @GetMapping("/caixa")
    public ResponseEntity<List<CaixaModel>> getAllCaixa() {
        List<CaixaModel> caixaModelList = caixaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(caixaModelList);
    }

    @GetMapping("/caixa/{id}")
    public ResponseEntity<CaixaModel> getOneCaixa(@PathVariable UUID id) {
        Optional<CaixaModel> caixa = caixaRepository.findById(id);
        return caixa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/caixa/{id}")
    public ResponseEntity<Object> updateCaixa(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid CaixaRecordDto caixaRecordDto) {
        Optional<CaixaModel> caixaOptional = caixaRepository.findById(id);
        if (!caixaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Valor não encontrado");
        }

        var caixaModel = caixaOptional.get();
        BeanUtils.copyProperties(caixaRecordDto, caixaModel);
        return ResponseEntity.status(HttpStatus.OK).body(caixaRepository.save(caixaModel));
    }

    @DeleteMapping("/caixa/{id}")
    public ResponseEntity<Object> deleteCaixa(@PathVariable(value = "id") UUID id) {
        Optional<CaixaModel> caixa = caixaRepository.findById(id);
        if (caixa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Valor não encontrado");
        }
        caixaRepository.delete(caixa.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Valor removido com sucesso");
    }
}
