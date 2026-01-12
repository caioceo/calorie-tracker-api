package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientRepository extends JpaRepository<Client, String> {

    UserDetails findByEmail(String email);
}