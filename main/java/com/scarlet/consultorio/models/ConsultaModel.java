package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "consulta")
public class ConsultaModel {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID idConsulta;
    private LocalDate dataConsulta;
    private String descricao;
    private double valor;
}
