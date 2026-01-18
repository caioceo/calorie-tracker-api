package dev.caio.fitsy.service;

import dev.caio.fitsy.dto.mapper.MetaMapper;
import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.request.UpdateMetaRequest;
import dev.caio.fitsy.dto.response.HistoricoMetaReponse;
import dev.caio.fitsy.dto.response.MetaResponse;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.model.Meta;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.model.UserInfo;
import dev.caio.fitsy.model.enums.Status;
import dev.caio.fitsy.repository.MetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MetaService {

    private final MetaRepository metaRepository;
    private final MetaMapper metaMapper;
    private final MetaNutrientesService metaNutrientesService;

    public MetaService(MetaRepository metaRepository, MetaMapper metaMapper, MetaNutrientesService metaNutrientesService) {
        this.metaRepository = metaRepository;
        this.metaMapper = metaMapper;
        this.metaNutrientesService = metaNutrientesService;
    }

    @Transactional
    public MetaResponse createNewMeta(User user, CreateMetaRequest request) {

        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta metaAtiva = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);
        if(metaAtiva==null){

        }
        else if(metaAtiva.getObjetivo().equals( request.objetivo())){
            throw new BusinessException("Já existe uma meta ativa igual a essa para o usuário.");
        }
        else{
            if(metaAtiva.getDataInicio().equals(LocalDate.now())){
                metaRepository.delete(metaAtiva);
            }
            else{
                metaAtiva.setStatus(Status.INATIVO);
            }
        }

        Meta meta = metaMapper.createRequestToModel(request);
        meta.setPesoInicial(user.getUserInfo().getPeso());
        meta.setUserInfo(userInfo);
        metaRepository.save(meta);

        metaNutrientesService.createMetaNutrientes(user, new MetaNutrientesRequest(35,40, 25));


        return metaMapper.modelToResponse(HttpStatus.CREATED.value(), "Nova meta criado com sucesso!", meta);
    }

    public boolean isEqualToUpdate(Meta meta, UpdateMetaRequest request){
        boolean nivelAtividadeEqual = (request.nivel_atividade() == null || request.nivel_atividade().equals(meta.getNivelAtividade()));
        boolean pesoMetaEqual = (request.peso_meta() == null || request.peso_meta().equals(meta.getPesoInicial()));
        boolean estrategiaEqual = (request.estrategia() == null || request.estrategia().equals(meta.getEstrategia()));

        return nivelAtividadeEqual && pesoMetaEqual && estrategiaEqual;
    }

    public MetaResponse updateActiveMeta(User user, UpdateMetaRequest request) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta metaAtiva = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);

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
        return metaMapper.modelToResponse(HttpStatus.OK.value(), "Meta atualizada com sucesso!", metaAtiva);
    }

    public MetaResponse getActiveMeta(User user) {

        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }

        Meta metaAtiva = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);

        if(metaAtiva==null){
            throw new BusinessException("Não existe uma meta ativa para o usuário.");
        }

        return metaMapper.modelToResponse(HttpStatus.OK.value(), "Meta ativa encontrada com sucesso!", metaAtiva);
    }

    public List<HistoricoMetaReponse> getHistoricoMetas(User user) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        List<Meta> metas = metaRepository.findByUserInfoOrderByDataInicioDesc(userInfo);

        return metaMapper.modelListToResponseList(metas);
    }

}
