package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
}