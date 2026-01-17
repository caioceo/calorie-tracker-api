package dev.caio.fitsy.dto.request;

import jakarta.validation.constraints.*;

public record UpdatePesoRequest(
        @NotNull(message = "Peso é obrigatório") @Positive(message = "Valor deve ser positivo") @Min(30) @Max(400) Float peso
) {
}
