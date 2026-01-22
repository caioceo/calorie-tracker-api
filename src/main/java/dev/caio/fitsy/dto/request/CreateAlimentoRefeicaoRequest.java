package dev.caio.fitsy.dto.request;

import dev.caio.fitsy.model.enums.RefeicaoNome;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateAlimentoRefeicaoRequest(
    @PastOrPresent(message = "Data inválida") LocalDate data,
    @NotNull(message = "Nome da refeição é obrigatório") RefeicaoNome refeicao_nome,
    Long alimento_id,
    @Positive(message = "Quantidade inválida") Float quantidade
) {
}
