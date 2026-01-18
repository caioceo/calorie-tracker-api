package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.response.HistoricoPesoResponse;
import dev.caio.fitsy.model.HistoricoPeso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoricoPesoMapper {

    public List<HistoricoPesoResponse> modelListToResponseList(List<HistoricoPeso> historicoPesos){
        return historicoPesos
                .stream()
                .map(historicoPeso -> new HistoricoPesoResponse(
                        historicoPeso.getStatus(), historicoPeso.getDataRegistro(), historicoPeso.getPeso()))
                .toList();
    }
}
