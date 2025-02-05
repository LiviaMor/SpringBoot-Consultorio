package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table (name = "anamnese")
public class AnamneseModel {
@Id
@GeneratedValue (strategy = GenerationType.UUID)
    private UUID idAnamnese;
    private UUID idPaciente;
    @Column (name = "queixaPrincipal")
    private String queixaPrincipal;
    @Column (name = "historicoMedico")
    private String historicoMedico;
    @Column (name = "alergias")
    private Boolean alergias;
    @Column (name = "medicamento")
    private Boolean medicamento;
    @Column (name = "cirugia")
    private Boolean cirurgia;
    @Column (name = "diabetes")
    private Boolean diabetes;
    @Column (name = "hipertensao")
    private Boolean hipertensao;
    @Column (name = "habitos")
    private Boolean habitos;

    }
