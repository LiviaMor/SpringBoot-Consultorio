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
    private String queixaPrincipal;
    private String historicoMedico;
    }
