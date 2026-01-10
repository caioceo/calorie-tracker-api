package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
}