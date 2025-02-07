package com.scarlet.consultorio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record ConsultaRecordDto(@NotBlank String descricao, @NotNull LocalDate dataConsulta, @NotNull double valor) {
}
