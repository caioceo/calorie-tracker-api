package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.HistoricoPeso;
import dev.caio.fitsy.model.UserInfo;
import dev.caio.fitsy.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoricoPesoRepository extends JpaRepository<HistoricoPeso, Long> {

    List<HistoricoPeso> findByUserInfoOrderByDataRegistroDesc(UserInfo userInfo);

    HistoricoPeso findByUserInfoAndStatus(UserInfo userInfo, Status status);


}