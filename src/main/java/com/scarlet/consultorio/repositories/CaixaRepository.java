package com.scarlet.consultorio.repositories;

import com.scarlet.consultorio.models.CaixaModel;
import com.scarlet.consultorio.models.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaModel, UUID> {
  }
