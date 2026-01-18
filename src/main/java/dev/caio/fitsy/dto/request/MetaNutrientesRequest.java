package dev.caio.fitsy.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MetaNutrientesRequest(
    @NotNull(message = "Proteina é obrigatório")
    @Max(value = 80, message = "80% é a quantidade maxima por macro")
    @Min(value = 10, message = "10% é o percentual mínimo por macro") Integer proteina,

    @NotNull(message = "Carboidrato é obrigatório")
    @Max(value = 80, message = "80% é a quantidade maxima por macro")
    @Min(value = 10, message = "10% é o percentual mínimo por macro") Integer carboidrato,

    @NotNull(message = "Gorduara é obrigatório")
    @Max(value = 80, message = "80% é a quantidade maxima por macro")
    @Min(value = 10, message = "10% é o percentual mínimo por macro") Integer gordura
) {}


