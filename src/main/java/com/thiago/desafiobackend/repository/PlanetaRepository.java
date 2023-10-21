package com.thiago.desafiobackend.repository;

import com.thiago.desafiobackend.model.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanetaRepository extends JpaRepository<Planeta, Integer> {
}
