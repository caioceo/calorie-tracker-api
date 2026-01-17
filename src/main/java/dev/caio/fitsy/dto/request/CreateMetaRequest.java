package dev.caio.fitsy.dto.request;

import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;
import jakarta.validation.constraints.*;

public record CreateMetaRequest(
        @NotNull(message ="Objetivo é obrigatório" ) Objetivo objetivo,
        @NotNull(message = "Estratégia é obrigatória") Estrategia estrategia,
        @NotNull(message = "Peso alvo é obrigatório") @Positive(message = "Valor deve ser positivo") @Min(30) @Max(200) Float peso_meta,
        @NotNull( message = "Nível de atividade física é obrigatória") NivelAtividade nivel_atividade) {
}
