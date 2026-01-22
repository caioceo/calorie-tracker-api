package dev.caio.fitsy.repository.alimentacao;

import dev.caio.fitsy.model.alimentacao.CategoriaAlimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaAlimentoRepository extends JpaRepository<CategoriaAlimento, Long> {
}