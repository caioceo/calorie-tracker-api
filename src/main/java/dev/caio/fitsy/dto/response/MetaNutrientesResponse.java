package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.Status;

import java.time.LocalDate;

public record MetaNutrientesResponse(
        Status status,
        LocalDate data_inicio,
        LocalDate data_fim,
        Float calorias,
        Float proteinas,
        Float carboidratos,
        Float gorduras,
        Float fibras,
        Float colesterol,
        Float sodio,
        Float potassio,
        Float ferro,
        Float calcio,
        Float zinco,
        Float vitamina_c,
        Float magnesio
) {
}
