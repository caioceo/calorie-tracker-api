package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}