package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Meta;
import dev.caio.fitsy.model.MetaNutrientes;
import dev.caio.fitsy.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaNutrientesRepository extends JpaRepository<MetaNutrientes, Long> {

    MetaNutrientes findByMetaAndStatus(Meta meta, Status status);
}