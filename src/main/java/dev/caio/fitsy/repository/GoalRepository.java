package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Client.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, String> {
}