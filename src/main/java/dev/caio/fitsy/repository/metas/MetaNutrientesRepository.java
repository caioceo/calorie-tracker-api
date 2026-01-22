package dev.caio.fitsy.repository.metas;

import dev.caio.fitsy.model.enums.Status;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.metas.MetaNutrientes;
import dev.caio.fitsy.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MetaNutrientesRepository extends JpaRepository<MetaNutrientes, Long> {

    MetaNutrientes findByMetaAndDataFimIsNull(Meta meta);


    MetaNutrientes findByMetaAndStatus(Meta meta, Status status);

    @Query("""
    SELECT mn
    FROM UserInfo ui
    JOIN ui.metas m
    JOIN m.metaNutrientes mn
    WHERE ui = :userInfo
        AND mn.dataInicio <= :data
            AND (mn.dataFim IS NULL OR :data <= mn.dataFim)
    """)
    MetaNutrientes findByUserInfoIdAndData(
            @Param("userInfo") UserInfo userInfo,
            @Param("data") LocalDate data
    );
}