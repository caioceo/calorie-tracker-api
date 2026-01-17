package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.HistoricoPeso;
import dev.caio.fitsy.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HistoricoPesoRepository extends JpaRepository<HistoricoPeso, Long> {

    List<HistoricoPeso> findByUserInfoOrderByDataRegistroDesc(UserInfo userInfo);

}