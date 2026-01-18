package dev.caio.fitsy.service;

import dev.caio.fitsy.dto.mapper.MetaNutrientesMapper;
import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.response.MetaNutrientesResponse;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.*;
import dev.caio.fitsy.model.enums.*;
import dev.caio.fitsy.repository.DiarioAlimentarRepository;
import dev.caio.fitsy.repository.MetaNutrientesRepository;
import dev.caio.fitsy.repository.MetaRepository;
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

    public MetaNutrientesResponse getActiveMetaNutrientes(User user) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }
        Meta meta = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);
        if(meta == null) {
            throw new NotFoundException("Meta", "user", user.getId());
        }

        MetaNutrientes model = metaNutrientesRepository.findByMetaAndStatus(meta, Status.ATIVO);
        if(model == null){
            throw new NotFoundException("Meta nutricional", "meta", meta.getId());
        }
        return mapper.modelToResponse(model);
    }

    public MetaNutrientesResponse createMetaNutrientes(User user, MetaNutrientesRequest request) {

        if(request.proteina() + request.carboidrato() + request.gordura() != 100) {
            throw new BusinessException("A soma dos percentuais de cada macro deve ser igual a 100%.");
        }

        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }

        Meta meta = metaRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);
        if(meta == null) {
            throw new NotFoundException("Meta", "userInfo", userInfo.getId());
        }

        MetaNutrientes metaNutrientes = metaNutrientesRepository.findByMetaAndStatus(meta, Status.ATIVO);
        if(metaNutrientes != null){
                if(metaNutrientes.getDataInicio().equals(LocalDate.now())){
                    metaNutrientesRepository.delete(metaNutrientes);
                }else {
                    metaNutrientes.setStatus(Status.INATIVO);
                    metaNutrientes.setDataFim(LocalDate.now());
                    metaNutrientesRepository.save(metaNutrientes);
                }
        }

        MetaNutrientes newMetaNutrientes = mapper.createRequestToModel(request);
        setMacroNutrients(newMetaNutrientes, meta, userInfo, request);
        newMetaNutrientes.setMeta(meta);

        setMetaNutrienteToDiario(newMetaNutrientes);
        metaNutrientesRepository.save(newMetaNutrientes);

        return mapper.modelToResponse(newMetaNutrientes);
    }

    public void setMetaNutrienteToDiario(MetaNutrientes metaNutrientes){
        UserInfo userInfo = metaNutrientes.getMeta().getUserInfo();

        DiarioAlimentar diario = diarioAlimentarRepository.findByUserInfoAndDataRegistro(userInfo, LocalDate.now());
        if(diario == null){
            return;
        }
        diario.setMetaNutrientes(metaNutrientes);
        diario.setUserInfo(userInfo);
        diario.setDataRegistro(LocalDate.now());
        diarioAlimentarRepository.save(diario);
    }

    public void setMacroNutrients(MetaNutrientes metaNutrientes, Meta meta, UserInfo userInfo, MetaNutrientesRequest request){
        float tmb = 10*(userInfo.getPeso()) + 6.25f*(userInfo.getAltura()) - 5* getIdade(userInfo.getDataNascimento()) + (userInfo.getSexo().equals(Sexo.MASCULINO) ? 5 : -161);
        float tdeE = tmb * getNivelAtividade(meta);
        float calorias = tdeE * FatorObjetivoEstrategia(meta);

        metaNutrientes.setCalorias(calorias);
        metaNutrientes.setProteinas( (calorias * request.proteina() / 100) / 4);
        metaNutrientes.setCarboidratos( (calorias * request.carboidrato() / 100) / 4);
        metaNutrientes.setGorduras( (calorias * request.gordura() / 100) / 9);

    }

    public Integer getIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
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
