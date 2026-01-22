package dev.caio.fitsy.repository.alimentacao;

import dev.caio.fitsy.model.alimentacao.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
}