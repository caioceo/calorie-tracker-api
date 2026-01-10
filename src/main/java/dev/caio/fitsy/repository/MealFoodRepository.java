package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.MealFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealFoodRepository extends JpaRepository<MealFood, UUID> {
}