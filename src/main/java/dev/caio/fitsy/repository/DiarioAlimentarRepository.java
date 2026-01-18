package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.DiarioAlimentar;
import dev.caio.fitsy.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DiarioAlimentarRepository extends JpaRepository<DiarioAlimentar, Long> {

    DiarioAlimentar findByUserInfoAndDataRegistro(UserInfo userInfoId, LocalDate data);
}