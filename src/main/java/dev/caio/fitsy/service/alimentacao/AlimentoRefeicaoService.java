package dev.caio.fitsy.service.alimentacao;
import dev.caio.fitsy.dto.request.CreateAlimentoRefeicaoRequest;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.alimentacao.Alimento;
import dev.caio.fitsy.model.alimentacao.AlimentoRefeicao;
import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.alimentacao.Refeicao;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.repository.alimentacao.AlimentoRefeicaoRepository;
import dev.caio.fitsy.repository.alimentacao.AlimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AlimentoRefeicaoService {
    private final AlimentoRefeicaoRepository alimentoRefeicaoRepository;
    private final RefeicaoService refeicaoService;
    private final DiarioAlimentarService diarioAlimentarService;
    private AlimentoRepository alimentoRepository;

    public AlimentoRefeicaoService(AlimentoRefeicaoRepository alimentoRefeicaoRepository,
                                   RefeicaoService refeicaoService, DiarioAlimentarService diarioAlimentarService,
                                   AlimentoRepository alimentoRepository) {

        this.alimentoRefeicaoRepository = alimentoRefeicaoRepository;

        this.refeicaoService = refeicaoService;
        this.diarioAlimentarService = diarioAlimentarService;
        this.alimentoRepository = alimentoRepository;
    }

    @Transactional
    public AlimentoRefeicao createAlimentoRefeicao(User user, CreateAlimentoRefeicaoRequest request) {

        DiarioAlimentar diarioAlimentar = diarioAlimentarService.getOrCreateDiarioAlimentar(user, request.data());
        Refeicao refeicao = refeicaoService.getOrCreateRefeicao(request.refeicao_nome(), diarioAlimentar);

        Alimento alimento = alimentoRepository.findById(request.alimento_id()).orElseThrow(() -> new NotFoundException("Alimento", "alimento", request.alimento_id()));

        AlimentoRefeicao alimentoRefeicaoExistente = alimentoRefeicaoRepository.findByRefeicaoAndAlimento(refeicao, alimento);
        if (alimentoRefeicaoExistente != null) {
            float quantidadeSomada = alimentoRefeicaoExistente.getQuantidade() + request.quantidade();
            alimentoRefeicaoExistente.setQuantidade(quantidadeSomada);
            alimentoRefeicaoExistente.setNutrientes(alimentoRefeicaoExistente.getAlimento());
            return alimentoRefeicaoRepository.save(alimentoRefeicaoExistente);
        }

        AlimentoRefeicao alimentoRefeicao = new AlimentoRefeicao();
        alimentoRefeicao.setAlimento(alimento);
        alimentoRefeicao.setQuantidade(request.quantidade());
        alimentoRefeicao.setNutrientes(alimento);
        alimentoRefeicao.setRefeicao(refeicao);
        alimentoRefeicaoRepository.save(alimentoRefeicao);
               return alimentoRefeicao;
    }

//    // TODO REFATOR DAQUI PRA BAIXO
//    public AlimentoRefeicaoResponse updateAlimentoRefeicao(User user, UpdateAlimentoRefeicaoRequest request) {
//        UserInfo userInfo = user.getUserInfo();
//        if (userInfo == null) {
//            throw new NotFoundException("UserInfo", "user", user.getId());
//        }
//
//        AlimentoRefeicao alimentoRefeicao = alimentoRefeicaoRepository.findById(request.aliemnto_refeicao_id())
//                .orElseThrow(() -> new NotFoundException("AlimentoRefeicao", "object", request.aliemnto_refeicao_id()));
//
//        //  TODO change to query to check ownership
//        if (!alimentoRefeicao.getRefeicao().getDiarioAlimentar().getUserInfo().getId().equals(userInfo.getId())) {
//            throw new NotFoundException("AlimentoRefeicao", "object", request.aliemnto_refeicao_id());
//        }
//
//        if (alimentoRefeicao.getQuantidade().equals(request.quantidade())) {
//            throw new BusinessException("A quantidade informada é igual a atual.");
//        }
//
//        alimentoRefeicao.setQuantidade(request.quantidade());
//
//        return mapper.modelToResponse(alimentoRefeicaoRepository.save(alimentoRefeicao));
//    }
//
//    // TODO verify if exists another Refeicao in DiarioAlimentar to decide to delete or not DiarioAlimentar
//    @Transactional
//    public String deleteAlimentoRefeicao(User user, Long alimentoRefeicaoId) {
//        UserInfo userInfo = user.getUserInfo();
//        if (userInfo == null) {
//            throw new NotFoundException("UserInfo", "user", user.getId());
//        }
//
//        // TODO query to check ownership
//        AlimentoRefeicao alimentoRefeicao = alimentoRefeicaoRepository.findById(alimentoRefeicaoId)
//                .orElseThrow(() -> new NotFoundException("AlimentoRefeicao", "object", alimentoRefeicaoId));
//
//        if (!alimentoRefeicao.getRefeicao().getDiarioAlimentar().getUserInfo().getId().equals(userInfo.getId())) {
//            throw new NotFoundException("AlimentoRefeicao", "user", user.getId());
//        }
//
//        alimentoRefeicaoRepository.delete(alimentoRefeicao);
//        return "Alimento removido da refeição com sucesso.";
//    }
//
//    public String deleteRefeicao(User user, Long refeicaoId) {
//        UserInfo userInfo = user.getUserInfo();
//        if (userInfo == null) {
//            throw new NotFoundException("UserInfo", "user", user.getId());
//        }
//
//        Refeicao refeicao = refeicaoRepository.findById(refeicaoId)
//                .orElseThrow(() -> new NotFoundException("Refeicao", "object", refeicaoId));
//
//        if (!refeicao.getDiarioAlimentar().getUserInfo().getId().equals(userInfo.getId())) {
//            throw new NotFoundException("Refeicao", "user", user.getId());
//        }
//
//        refeicaoRepository.delete(refeicao);
//        return "Refeição removida com sucesso.";
//    }
//
//    public String updateRefeicaoName(User user, Long refeicaoId, RefeicaoNome novoNome) {
//        UserInfo userInfo = user.getUserInfo();
//        if (userInfo == null) {
//            throw new NotFoundException("UserInfo", "user", user.getId());
//        }
//
//        Refeicao refeicao = refeicaoRepository.findById(refeicaoId)
//                .orElseThrow(() -> new NotFoundException("Refeicao", "object", refeicaoId));
//
//        if (!refeicao.getDiarioAlimentar().getUserInfo().getId().equals(userInfo.getId())) {
//            throw new NotFoundException("Refeicao", "user", user.getId());
//        }
//
//        refeicao.setNome(novoNome);
//        refeicaoRepository.save(refeicao);
//        return "Nome da refeição atualizado com sucesso.";
//    }
//
//    public String deleteDiarioAlimentar(User user, Long diarioAlimentarId) {
//        UserInfo userInfo = user.getUserInfo();
//        if (userInfo == null) {
//            throw new NotFoundException("UserInfo", "user", user.getId());
//        }
//
//        DiarioAlimentar diarioAlimentar = diarioAlimentarRepository.findById(diarioAlimentarId).orElseThrow(() -> new NotFoundException("DiarioAlimentar", "object", diarioAlimentarId));
//
//        if (!diarioAlimentar.getUserInfo().getId().equals(userInfo.getId())) {
//            throw new NotFoundException("DiarioAlimentar", "user", user.getId());
//        }
//
//        diarioAlimentarRepository.delete(diarioAlimentar);
//        return "Diário alimentar removido com sucesso.";
//    }

}
