package dev.caio.fitsy.dto.request;

import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import jakarta.validation.constraints.NotNull;

public record UpdateMetaRequest (
        Estrategia estrategia,
        Float peso_meta,
        NivelAtividade nivel_atividade
) {
}
