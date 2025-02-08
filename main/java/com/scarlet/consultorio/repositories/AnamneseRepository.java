package com.scarlet.consultorio.repositories;

import com.scarlet.consultorio.models.AnamneseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnamneseRepository extends JpaRepository<AnamneseModel, UUID> {
}
