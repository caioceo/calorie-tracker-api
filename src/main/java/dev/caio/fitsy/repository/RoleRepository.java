package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}