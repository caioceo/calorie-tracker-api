package dev.caio.fitsy.service.user;

import dev.caio.fitsy.dto.mapper.HistoricoPesoMapper;
import dev.caio.fitsy.dto.response.HistoricoPesoResponse;
import dev.caio.fitsy.model.user.HistoricoPeso;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.model.enums.Status;
import dev.caio.fitsy.repository.user.HistoricoPesoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoricoPesoService {
    private final HistoricoPesoRepository historicoPesoRepository;

    public HistoricoPesoService(HistoricoPesoRepository historicoPesoRepository) {
        this.historicoPesoRepository = historicoPesoRepository;
    }

    public void appendNewPeso(UserInfo userInfo){
        HistoricoPeso peso = historicoPesoRepository.findByUserInfoAndStatus(userInfo, Status.ATIVO);

        if(peso!=null){
            if(peso.getDataRegistro().equals(LocalDate.now())){
                historicoPesoRepository.delete(peso);
            }
            else{
                peso.setStatus(Status.INATIVO);
                historicoPesoRepository.save(peso);
            }
        }

        HistoricoPeso novoPeso = new HistoricoPeso();
        novoPeso.setUserInfo(userInfo);
        novoPeso.setPeso(userInfo.getPeso());
        novoPeso.setDataRegistro(LocalDate.now());
        novoPeso.setStatus(Status.ATIVO);

        historicoPesoRepository.save(novoPeso);
    }

    public List<HistoricoPeso> getHistoricoPeso(User user){
        List<HistoricoPeso> historicoPeso = historicoPesoRepository.findByUserInfoOrderByDataRegistroDesc(user.getUserInfo());

        return historicoPeso;
    }
}
