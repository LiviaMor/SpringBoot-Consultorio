package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@ToString
@Table (name = "caixa")
public class CaixaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCaixa;
    @Column (name = "dataEntrada")
    private LocalDate dataEntrada;
    @Column (name = "valorEntrada")
    private double valorEntrada;
    @Column (name = "valorSaida")
    private double valorSaida;


}
