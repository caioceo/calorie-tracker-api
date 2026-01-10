package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Client.WeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightHistoryRepository extends JpaRepository<WeightHistory, String> {
}