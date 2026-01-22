package dev.caio.fitsy.service.alimentacao;

import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.model.alimentacao.DiarioAlimentar;
import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;
import dev.caio.fitsy.model.metas.MetaNutrientes;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.repository.alimentacao.DiarioAlimentarRepository;
import dev.caio.fitsy.repository.metas.MetaNutrientesRepository;
import dev.caio.fitsy.repository.metas.MetaRepository;
import dev.caio.fitsy.service.metas.MetaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class DiarioAlimentarService {
    private DiarioAlimentarRepository diarioAlimentarRepository;
    private MetaNutrientesRepository metaNutrientesRepository;
    private final MetaRepository metaRepository;
    private final MetaService metaService;

    public DiarioAlimentarService(DiarioAlimentarRepository diarioAlimentarRepository, MetaNutrientesRepository metaNutrientesRepository,
                                  MetaRepository metaRepository, MetaService metaService) {
        this.diarioAlimentarRepository = diarioAlimentarRepository;
        this.metaNutrientesRepository = metaNutrientesRepository;
        this.metaRepository = metaRepository;
        this.metaService = metaService;
    }

    public DiarioAlimentar getOrCreateDiarioAlimentar(User user, LocalDate data) {
        UserInfo userInfo = user.getUserInfo();
        if(userInfo == null){
            throw new BusinessException("O usuário não possui informações cadastradas.");
        }

        if(!metaRepository.existsByUserInfo(userInfo)){
            metaRepository.save(metaService.createNewMeta(user, new CreateMetaRequest(
                    Objetivo.MANTER_PESO,
                    Estrategia.CONSERVADORA,
                    userInfo.getPeso(),
                    NivelAtividade.MODERADO
            )));
        }

        DiarioAlimentar diarioAlimentar = diarioAlimentarRepository.findByUserInfoAndDataRegistro(userInfo, data);
        if(diarioAlimentar==null){
            diarioAlimentar = new DiarioAlimentar();
            diarioAlimentar.setDataRegistro(data);
            diarioAlimentar.setUserInfo(userInfo);
            MetaNutrientes metaNutrientes = metaNutrientesRepository.findByUserInfoIdAndData(userInfo, data);
            diarioAlimentar.setMetaNutrientes(metaNutrientes);
        }
        return diarioAlimentarRepository.save(diarioAlimentar);
    }
}
