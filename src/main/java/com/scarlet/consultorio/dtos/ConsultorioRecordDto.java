package com.scarlet.consultorio.dtos;

import jakarta.validation.constraints.NotBlank;

public record ConsultorioRecordDto(@NotBlank String nomeConsultorio, @NotBlank String enderecoConsultorio ) {
}
