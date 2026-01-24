package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.response.AlimentoRefeicaoResponse;
import dev.caio.fitsy.model.alimentacao.AlimentoRefeicao;
import org.springframework.stereotype.Component;

@Component
public class AlimentoRefeicaoMapper {

    public AlimentoRefeicaoResponse modelToResponse(AlimentoRefeicao model) {
        return new AlimentoRefeicaoResponse(model.getId(),model.getAlimento().getNome(),  model.getQuantidade(),
                model.getCalorias(), model.getProteinas(), model.getCarboidratos(), model.getGorduras(),
                model.getFibras(), model.getColesterol(), model.getSodio(), model.getPotassio(),
                model.getFerro(), model.getCalcio());
    }
}
