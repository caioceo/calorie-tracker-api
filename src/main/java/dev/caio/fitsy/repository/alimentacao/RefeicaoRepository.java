package dev.caio.fitsy.repository.alimentacao;
import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.alimentacao.Refeicao;
import dev.caio.fitsy.model.enums.RefeicaoNome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {

    Refeicao findByDiarioAlimentarAndNome(DiarioAlimentar diarioAlimentar, RefeicaoNome nome);

}