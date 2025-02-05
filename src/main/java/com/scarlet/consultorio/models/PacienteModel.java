package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "paciente")
public class PacienteModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long idPaciente;
    private String nomePaciente;
    private String cpfPaciente;
    private String rgPaciente;
    private String enderecoPaciente;
    private String telefonePaciente;
    private String emailPaciente;
    private Date dataNascimentoPaciente;
    private String sexoPaciente;
}