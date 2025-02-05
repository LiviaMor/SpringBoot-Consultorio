package com.scarlet.consultorio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "anamnese")
public class AnamneseModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long idAnamnese;
    private Long idPaciente;
    private String queixaPrincipal;
    private String historicoMedico;
    }
