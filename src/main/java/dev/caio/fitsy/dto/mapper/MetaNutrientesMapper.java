package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.response.MetaNutrientesResponse;
import dev.caio.fitsy.model.MetaNutrientes;
import dev.caio.fitsy.model.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MetaNutrientesMapper {

    public MetaNutrientesResponse modelToResponse(MetaNutrientes meta) {
        MetaNutrientesResponse response = new MetaNutrientesResponse(
                meta.getStatus(),
                meta.getDataInicio(),
                meta.getDataFim(),
                meta.getCalorias(),
                meta.getProteinas(),
                meta.getCarboidratos(),
                meta.getGorduras(),
                meta.getFibras(),
                meta.getColesterol(),
                meta.getSodio(),
                meta.getPotassio(),
                meta.getFerro(),
                meta.getCalcio(),
                meta.getZinco(),
                meta.getVitaminaC(),
                meta.getMagnesio()
        );

        return response;
    }

    public MetaNutrientes createRequestToModel(MetaNutrientesRequest request){
        MetaNutrientes model = new MetaNutrientes();
        model.setProteinas((float)request.proteina());
        model.setCarboidratos((float)request.carboidrato());
        model.setGorduras((float)request.gordura());
        model.setStatus(Status.ATIVO);
        model.setDataInicio(LocalDate.now());
        return model;
    }

    public void updateRequestToModel(MetaNutrientes changes){
        MetaNutrientes model = new MetaNutrientes();
        model.setProteinas(changes.getProteinas());
        model.setCarboidratos(changes.getCarboidratos());
        model.setGorduras(changes.getGorduras());
    }
}
