package dev.caio.fitsy.service.metas;

import dev.caio.fitsy.dto.mapper.MetaMapper;
import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.request.UpdateMetaRequest;

import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.metas.MetaNutrientes;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.model.enums.Status;
import dev.caio.fitsy.repository.metas.MetaRepository;
import dev.caio.fitsy.repository.user.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MetaService {

    private final MetaRepository metaRepository;
    private final MetaMapper metaMapper;
    private final MetaNutrientesService metaNutrientesService;
    private final UserInfoRepository userInfoRepository;

    public MetaService(UserInfoRepository userInfoRepository ,MetaRepository metaRepository, MetaMapper metaMapper, MetaNutrientesService metaNutrientesService) {
        this.metaRepository = metaRepository;
        this.metaMapper = metaMapper;
        this.metaNutrientesService = metaNutrientesService;
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public Meta createNewMeta(User user, CreateMetaRequest request) {
        UserInfo userInfo = userInfoRepository.findByUser(user);
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta metaAtiva = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);
        if(metaAtiva != null && metaAtiva.getObjetivo().equals(request.objetivo())){
            throw new BusinessException("Para editar a meta atual, utilize o endpoint de atualização.");
        }
        if(metaAtiva == null){
            metaAtiva = new Meta();
            metaAtiva = metaMapper.createRequestToModel(request, metaAtiva);
        }
        if (metaAtiva.getDataInicio().equals(LocalDate.now())) {
            metaAtiva = metaMapper.createRequestToModel(request, metaAtiva);
        }
        else{
            metaAtiva.setStatus(Status.INATIVO);
            metaAtiva.setDataFim(LocalDate.now().minusDays(1));
        }
        metaAtiva.setPesoInicial(userInfo.getPeso());
        metaAtiva.setUserInfo(userInfo);
        metaRepository.save(metaAtiva);

        MetaNutrientes metaNutrientes = metaNutrientesService.createForUser(
                user, new MetaNutrientesRequest(35, 40,25)
        );

        return metaAtiva;
    }

    public boolean isEqualToUpdate(Meta meta, UpdateMetaRequest request){
        boolean nivelAtividadeEqual = (request.nivel_atividade() == null || request.nivel_atividade().equals(meta.getNivelAtividade()));
        boolean pesoMetaEqual = (request.peso_meta() == null || request.peso_meta().equals(meta.getPesoInicial()));
        boolean estrategiaEqual = (request.estrategia() == null || request.estrategia().equals(meta.getEstrategia()));

        return nivelAtividadeEqual && pesoMetaEqual && estrategiaEqual;
    }

    public Meta updateActiveMeta(User user, UpdateMetaRequest request) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta metaAtiva = metaRepository.findByUserInfoAndDataFimIsNull(userInfo);

        if(metaAtiva==null){
            throw new BusinessException("Não existe uma meta ativa para o usuário.");
        }
        if(isEqualToUpdate(metaAtiva, request)){
            throw new BusinessException("Nenhum campo para atualização foi fornecido.");
        }

        if(request.nivel_atividade()!= null){
            metaAtiva.setNivelAtividade(request.nivel_atividade());
        }
        if(request.peso_meta()!= null){
            metaAtiva.setPesoInicial(request.peso_meta());
        }

        if(request.estrategia()!=null){
            metaAtiva.setEstrategia(request.estrategia());
        }

        metaRepository.save(metaAtiva);
        return metaAtiva;
    }

    public Meta getActiveMeta(User user) {

        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }

        Meta metaAtiva = metaRepository.findByUserInfoAndDataFimIsNull(userInfo);

        if(metaAtiva==null){
            throw new BusinessException("Não existe uma meta ativa para o usuário.");
        }

        return metaAtiva;
    }

    public List<Meta> getHistoricoMetas(User user) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        List<Meta> metas = metaRepository.findByUserInfoOrderByDataInicioDesc(userInfo);

        return metas;
    }

}
