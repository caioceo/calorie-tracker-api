package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.response.HistoricoMetaReponse;
import dev.caio.fitsy.dto.response.MetaResponse;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MetaMapper {

    public MetaResponse modelToResponse(Integer status, Meta meta){
        return new MetaResponse(status, meta.getDataInicio(), meta.getPesoInicial(), meta.getPesoMeta(), meta.getObjetivo(), meta.getEstrategia(), meta.getNivelAtividade());
    }

    public Meta createRequestToModel(CreateMetaRequest request, Meta meta){
        meta.setObjetivo(request.objetivo());
        meta.setEstrategia(request.estrategia());
        meta.setPesoMeta(request.peso_meta());
        meta.setNivelAtividade(request.nivel_atividade());
        meta.setDataInicio(LocalDate.now());
        meta.setStatus(Status.ATIVO);
        return meta;
    }

    public List<HistoricoMetaReponse> modelListToResponseList(List<Meta> metas){
        return metas
                .stream()
                .map(meta -> new HistoricoMetaReponse(
                        meta.getDataInicio(),
                        meta.getPesoInicial(),
                        meta.getPesoMeta(),
                        meta.getObjetivo(),
                        meta.getEstrategia(),
                        meta.getNivelAtividade(),
                        meta.getStatus()
                ))
                .toList();
    }
}
