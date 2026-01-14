package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.MetaNutrientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaNutrientesRepository extends JpaRepository<MetaNutrientes, Long> {
}