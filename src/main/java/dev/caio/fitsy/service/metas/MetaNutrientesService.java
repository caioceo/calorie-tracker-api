package dev.caio.fitsy.service.metas;

import dev.caio.fitsy.dto.mapper.MetaNutrientesMapper;
import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.enums.*;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.metas.MetaNutrientes;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.repository.alimentacao.DiarioAlimentarRepository;
import dev.caio.fitsy.repository.metas.MetaNutrientesRepository;
import dev.caio.fitsy.repository.metas.MetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class MetaNutrientesService {
    private final MetaNutrientesRepository metaNutrientesRepository;
    private final MetaNutrientesMapper mapper;
    private final MetaRepository metaRepository;
    private final DiarioAlimentarRepository diarioAlimentarRepository;

    public MetaNutrientesService(MetaNutrientesRepository metaNutrientesRepository, MetaNutrientesMapper mapper, MetaRepository metaRepository, DiarioAlimentarRepository diarioAlimentarRepository) {
        this.metaNutrientesRepository = metaNutrientesRepository;
        this.mapper = mapper;
        this.metaRepository = metaRepository;
        this.diarioAlimentarRepository = diarioAlimentarRepository;
    }

    public MetaNutrientes getActiveMetaNutrientes(User user) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta meta = metaRepository.findByUserInfoAndDataFimIsNull(userInfo);
        if(meta == null) {
            throw new NotFoundException("Meta", "user", user.getId());
        }

        MetaNutrientes model = metaNutrientesRepository.findByMetaAndDataFimIsNull(meta);
        if(model == null){
            throw new NotFoundException("Meta nutricional", "meta", meta.getId());
        }
        return model;
    }

    @Transactional
    public MetaNutrientes createForUser(User user, MetaNutrientesRequest request){
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta meta = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);
        if(meta == null) {
            throw new NotFoundException("Meta", "user", user.getId());
        }
        if(request.proteina() + request.carboidrato() + request.gordura() != 100) {
            throw new BusinessException("A soma dos percentuais de cada macro deve ser igual a 100%.");
        }

        MetaNutrientes metaNutrientes = metaNutrientesRepository.findByMetaAndStatus(meta, Status.ATIVO);
        if (metaNutrientes== null) {
            metaNutrientes = new MetaNutrientes();
            metaNutrientes.setDataInicio(LocalDate.now());
        }
        return createMetaNutrientes(userInfo, request, meta, metaNutrientes);
    }

    @Transactional
    public MetaNutrientes createForMeta(Meta meta, MetaNutrientesRequest request) {
        MetaNutrientes metaNutrientes = new MetaNutrientes();
        metaNutrientes.setDataInicio(LocalDate.now());
        return createMetaNutrientes(meta.getUserInfo(), request, meta, metaNutrientes);
    }

    @Transactional
    public MetaNutrientes createMetaNutrientes(UserInfo userInfo, MetaNutrientesRequest request, Meta meta, MetaNutrientes metaNutrientes) {
        if(metaNutrientes.getDataInicio().equals(LocalDate.now())){
        }
        else{
            metaNutrientes.setStatus(Status.INATIVO);
            metaNutrientes.setDataFim(LocalDate.now().minusDays(1));
            metaNutrientesRepository.save(metaNutrientes);
            metaNutrientes = new MetaNutrientes();
        }
        setMacroNutrients(metaNutrientes, meta, request);
        metaNutrientes.setStatus(Status.ATIVO);
        metaNutrientes.setMeta(meta);
        return metaNutrientesRepository.save(metaNutrientes);
    }

    public void setMacroNutrients(MetaNutrientes metaNutrientes, Meta meta, MetaNutrientesRequest request){
        float tmb = 10*(meta.getUserInfo().getPeso()) + 6.25f*(meta.getUserInfo().getAltura()) - 5* getIdade(meta.getUserInfo().getDataNascimento()) + (meta.getUserInfo().getSexo().equals(Sexo.MASCULINO) ? 5 : -161);
        float tdeE = tmb * getNivelAtividade(meta);
        float calorias = tdeE * FatorObjetivoEstrategia(meta);

        metaNutrientes.setCalorias(calorias);
        metaNutrientes.setProteinas( (calorias * request.proteina() / 100) / 4);
        metaNutrientes.setCarboidratos( (calorias * request.carboidrato() / 100) / 4);
        metaNutrientes.setGorduras( (calorias * request.gordura() / 100) / 9);
    }

    Integer getIdade(LocalDate dataNascimento){
        Period gap = Period.between(dataNascimento, LocalDate.now());
        return gap.getYears();
    }


    public Float getNivelAtividade(Meta meta){
        if(meta.getNivelAtividade().equals(NivelAtividade.SEDENTARIO)) {return 1.2f;}
        else if(meta.getNivelAtividade().equals(NivelAtividade.LEVE)) {return 1.375f;}
        else if(meta.getNivelAtividade().equals(NivelAtividade.MODERADO)) {return 1.55f;}
        else if(meta.getNivelAtividade().equals(NivelAtividade.INTENSO)) {return 1.725f;}
        else if(meta.getNivelAtividade().equals(NivelAtividade.MUITO_INTENSO)) {return 1.9f;}
        else {
            throw new BusinessException("Nível de atividade inválido.");
        }
    }

    public Float FatorObjetivoEstrategia(Meta meta){
        if(meta.getObjetivo().equals(Objetivo.PERDER_PESO)){
            if(meta.getEstrategia().equals(Estrategia.CONSERVADORA)) {return 0.9f;}
            else if(meta.getEstrategia().equals(Estrategia.MODERADA)) {return 0.8f;}
            else if(meta.getEstrategia().equals(Estrategia.AGRESSIVA)) {return 0.7f;}
            else {
                throw new BusinessException("Estratégia inválida.");
            }
        }
        else if(meta.getObjetivo().equals(Objetivo.GANHAR_PESO)){
            if(meta.getEstrategia().equals(Estrategia.CONSERVADORA)) {return 1.05f;}
            else if(meta.getEstrategia().equals(Estrategia.MODERADA)) {return 1.15f;}
            else if(meta.getEstrategia().equals(Estrategia.AGRESSIVA)) {return 1.25f;}
            else {
                throw new BusinessException("Estratégia inválida.");
            }
        } else if (meta.getObjetivo().equals(Objetivo.MANTER_PESO)) {
            return 1.0f;
        } else {
            throw new BusinessException("Estratégia inválida.");
        }
    }

}
