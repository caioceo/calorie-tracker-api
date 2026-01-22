package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.HistoricoPesoMapper;
import dev.caio.fitsy.dto.response.HistoricoPesoResponse;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.service.user.HistoricoPesoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-info/historico-peso")
public class HistoricoPesoController {

    private final HistoricoPesoService historicoPesoService;
    private final HistoricoPesoMapper historicoPesoMapper;

    public HistoricoPesoController(HistoricoPesoService historicoPesoService, HistoricoPesoMapper historicoPesoMapper) {
        this.historicoPesoService = historicoPesoService;
        this.historicoPesoMapper = historicoPesoMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<HistoricoPesoResponse>> getHistoricoPeso(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(historicoPesoMapper.modelListToResponseList(historicoPesoService.getHistoricoPeso(user)));
    }
}
