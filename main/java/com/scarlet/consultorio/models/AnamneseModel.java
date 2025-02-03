package com.scarlet.consultorio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "anamnese")
public class AnamneseModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long idAnamnese;
    private Long idPaciente;
    private String queixaPrincipal;
    private String historicoMedico;
    }
