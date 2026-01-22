package dev.caio.fitsy.repository.alimentacao;

import dev.caio.fitsy.model.alimentacao.Alimento;
import dev.caio.fitsy.model.alimentacao.AlimentoRefeicao;
import dev.caio.fitsy.model.alimentacao.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlimentoRefeicaoRepository extends JpaRepository<AlimentoRefeicao, Long> {
    List<AlimentoRefeicao> findByRefeicao(Refeicao refeicao);

    AlimentoRefeicao findByRefeicaoAndAlimento(Refeicao refeicao, Alimento alimento);

}