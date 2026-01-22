package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.response.AlimentoRefeicaoResponse;
import dev.caio.fitsy.model.alimentacao.AlimentoRefeicao;
import org.springframework.stereotype.Component;

@Component
public class AlimentoRefeicaoMapper {

    public AlimentoRefeicaoResponse modelToResponse(AlimentoRefeicao model) {
        return new AlimentoRefeicaoResponse(model.getRefeicao().getNome(), model.getAlimento().getNome(),  model.getQuantidade(), "Caso o alimento ja esteja registrado na refeição, a quantidade será atualizada somando a quantidade informada.");
    }
}
