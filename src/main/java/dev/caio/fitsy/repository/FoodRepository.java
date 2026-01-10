package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}