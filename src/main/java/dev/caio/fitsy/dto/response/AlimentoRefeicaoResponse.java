package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.enums.RefeicaoNome;

public record AlimentoRefeicaoResponse (
    RefeicaoNome nome_refeicao,
    String nome_alimento,
    Float quantidade,
    String mensagem
){
}
