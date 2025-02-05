package com.scarlet.consultorio.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record PacienteRecordDto(
        @NotBlank String nomePaciente,
        @NotBlank String cpfPaciente,
        @NotBlank String rgPaciente,
        @NotBlank String enderecoPaciente,
        @NotBlank String telefonePaciente,
        @NotBlank String emailPaciente,
        @NotNull Date dataNascimentoPaciente,
        @NotBlank String sexoPaciente) {
}
