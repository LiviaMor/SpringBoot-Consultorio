package com.scarlet.consultorio.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import java.util.UUID;

@Entity
@Data
@Table(name = "consultorio")
public class ConsultorioModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private UUID idConsultorio;
    private String nomeConsultorio;
    private String enderecoConsultorio;
}