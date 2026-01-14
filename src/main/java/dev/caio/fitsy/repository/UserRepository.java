package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}