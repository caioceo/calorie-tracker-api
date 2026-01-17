package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;
import dev.caio.fitsy.model.enums.Status;

import java.time.LocalDate;

public record HistoricoMetaReponse(
        LocalDate data_registro,
        Float peso_inicial,
        Float peso_meta,
        Objetivo objetivo,
        Estrategia estrategia,
        NivelAtividade nivel_atividade,
        Status status
) {
}
