package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.CategoriaAlimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaAlimentoRepository extends JpaRepository<CategoriaAlimento, Long> {
}