package dev.caio.fitsy.dto.response;

import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.enums.RefeicaoNome;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public record AlimentoRefeicaoResponse (
    Long id,
    String nome_alimento,
    Float quantidade,
    Float calorias,
    Float proteinas,
    Float carboidratos,
    Float gorduras,
    Float fibras,
    Float colesterol,
    Float sodio,
    Float potassio,
    Float ferro,
    Float calcio
){
}
