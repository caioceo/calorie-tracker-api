package dev.caio.fitsy.service.alimentacao;
import dev.caio.fitsy.dto.request.UpdateAlimentoRefeicaoRequest;
import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.alimentacao.Refeicao;
import dev.caio.fitsy.model.enums.RefeicaoNome;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.repository.alimentacao.DiarioAlimentarRepository;
import dev.caio.fitsy.repository.alimentacao.RefeicaoRepository;
import org.springframework.stereotype.Service;

@Service
public class RefeicaoService {
    private final RefeicaoRepository refeicaoRepository;
    private final DiarioAlimentarRepository diarioAlimentarRepository;
    private final DiarioAlimentarService diarioAlimentarService;

    public RefeicaoService(RefeicaoRepository refeicaoRepository, DiarioAlimentarRepository diarioAlimentarRepository, DiarioAlimentarService diarioAlimentarService) {
        this.diarioAlimentarRepository = diarioAlimentarRepository;
        this.refeicaoRepository = refeicaoRepository;
        this.diarioAlimentarService = diarioAlimentarService;
    }


    public Refeicao getOrCreateRefeicao(RefeicaoNome nome, DiarioAlimentar diarioAlimentar) {

        Refeicao refeicao = refeicaoRepository.findByDiarioAlimentarAndNome(diarioAlimentar, nome);
        if(refeicao==null){
            refeicao = new Refeicao();
            refeicao.setDiarioAlimentar(diarioAlimentar);
            refeicao.setNome(nome);
            return refeicaoRepository.save(refeicao);
        }
        return refeicao;
    }

    public void updateRefeicao(User user, UpdateAlimentoRefeicaoRequest request) {

    }

    public void deleteRefeicao() {

    }

    public void getRefeicao() {

    }
}
