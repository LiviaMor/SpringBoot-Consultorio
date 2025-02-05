package com.scarlet.consultorio.models;


import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Entity
@Data
@Table(name = "consultorio")
public class ConsultorioModel {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idConsultorio;

    @Column(name = "nomeConsultorio")
    private String nomeConsultorio;
    @Column(name = "enderecoConsultorio")
    private String enderecoConsultorio;
}