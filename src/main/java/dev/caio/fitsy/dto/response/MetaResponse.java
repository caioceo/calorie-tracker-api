package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;

import java.time.LocalDate;

public record MetaResponse(
        Integer status,
        String mensagem,
        LocalDate data_inicio,
        Float peso_inicial,
        Float peso_meta,
        Objetivo objetivo,
        Estrategia estrategia,
        NivelAtividade nivel_atividade) {
}
