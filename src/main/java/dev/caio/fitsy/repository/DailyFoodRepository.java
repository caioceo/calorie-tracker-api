package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Food.DailyFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFoodRepository extends JpaRepository<DailyFood, String> {
}