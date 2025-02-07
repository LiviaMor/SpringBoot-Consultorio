package com.scarlet.consultorio.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CaixaRecordDto (@NotNull LocalDate dataEntrada, @NotNull double valorEntrada, @NotNull double valorSaida) {
}
