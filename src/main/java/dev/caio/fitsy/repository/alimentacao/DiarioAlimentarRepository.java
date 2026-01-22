package dev.caio.fitsy.repository.alimentacao;

import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DiarioAlimentarRepository extends JpaRepository<DiarioAlimentar, Long> {

    DiarioAlimentar findByUserInfoAndDataRegistro(UserInfo userInfoId, LocalDate data);

}