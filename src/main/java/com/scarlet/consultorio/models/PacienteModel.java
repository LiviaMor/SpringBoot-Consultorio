package com.scarlet.consultorio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "paciente")
public class PacienteModel {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idPaciente;

    @Column(name = "nomePaciente")
    private String nomePaciente;
    @Column(name = "cpfPaciente")
    private String cpfPaciente;
    @Column(name = "rgPaciente")
    private String rgPaciente;
    @Column(name = "enderecoPaciente")
    private String enderecoPaciente;
    @Column(name = "telefonePaciente")
    private String telefonePaciente;
    @Column(name = "emailPaciente")
    private String emailPaciente;
    @Column(name = "dataNascimentoPaciente")
    private Date dataNascimentoPaciente;
    @Column(name = "sexoPaciente")
    private String sexoPaciente;

    // Muitos pacientes pertencem a um consultório.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorio_id")
    private ConsultorioModel consultorio;

    // Um paciente possui muitas consultas.
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaModel> consultas;
    }