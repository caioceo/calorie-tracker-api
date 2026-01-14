package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Nutriente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrienteRepository extends JpaRepository<Nutriente, Long> {
}