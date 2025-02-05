package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table (name = "caixa")
public class CaixaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCaixa;
    @Column (name = "data")
    private LocalDate data;
    @Column (name = "valorEntrada")
    private double valorEntrada;
    @Column (name = "valorSaida")
    private double valorSaida;


}
