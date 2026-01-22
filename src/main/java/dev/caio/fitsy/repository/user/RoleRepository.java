package dev.caio.fitsy.repository.user;

import dev.caio.fitsy.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}