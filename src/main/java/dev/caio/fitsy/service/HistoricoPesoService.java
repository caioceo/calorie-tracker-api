package dev.caio.fitsy.service;

import dev.caio.fitsy.dto.mapper.HistoricoPesoMapper;
import dev.caio.fitsy.dto.response.HistoricoPesoResponse;
import dev.caio.fitsy.model.HistoricoPeso;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.model.UserInfo;
import dev.caio.fitsy.repository.HistoricoPesoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoricoPesoService {
    private final HistoricoPesoRepository historicoPesoRepository;
    private final HistoricoPesoMapper historicoPesoMapper;

    public HistoricoPesoService(HistoricoPesoRepository historicoPesoRepository, HistoricoPesoMapper historicoPesoMapper) {
        this.historicoPesoRepository = historicoPesoRepository;
        this.historicoPesoMapper = historicoPesoMapper;
    }

    public void appendNewPeso(UserInfo userInfo){
        HistoricoPeso novoPeso = new HistoricoPeso();
        novoPeso.setUserInfo(userInfo);
        novoPeso.setPeso(userInfo.getPeso());
        novoPeso.setDataRegistro(LocalDate.now());

        historicoPesoRepository.save(novoPeso);
    }

    public List<HistoricoPesoResponse> getHistoricoPeso(User user){
        List<HistoricoPeso> historicoPeso = historicoPesoRepository.findByUserInfoOrderByDataRegistroDesc(user.getUserInfo());

        return historicoPesoMapper.modelListToResponseList(historicoPeso);
    }
}
