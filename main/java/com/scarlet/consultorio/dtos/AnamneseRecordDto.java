package com.scarlet.consultorio.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AnamneseRecordDto(@NotBlank String queixaPrincipal,
    @NotBlank String historicoMedico,
    @NotNull Boolean alergias,
    @NotNull Boolean medicamento,
    @NotNull Boolean cirurgia,
    @NotNull Boolean diabetes,
    @NotNull Boolean hipertensao,
    @NotNull Boolean habitos,
    @NotNull Boolean coracao){

}
