package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
}