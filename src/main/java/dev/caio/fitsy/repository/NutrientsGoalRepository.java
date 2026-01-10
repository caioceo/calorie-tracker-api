package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.NutrientsGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsGoalRepository extends JpaRepository<NutrientsGoal, String> {
}