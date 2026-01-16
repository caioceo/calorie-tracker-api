package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<UserDetails> findByEmail(String username);

     boolean existsByEmail(String email);
}