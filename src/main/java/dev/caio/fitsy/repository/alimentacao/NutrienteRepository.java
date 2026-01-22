package dev.caio.fitsy.repository.alimentacao;

import dev.caio.fitsy.model.user.Nutriente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrienteRepository extends JpaRepository<Nutriente, Long> {
}