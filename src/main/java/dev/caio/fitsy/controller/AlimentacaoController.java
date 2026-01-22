package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.AlimentoRefeicaoMapper;
import dev.caio.fitsy.dto.response.AlimentoRefeicaoResponse;
import dev.caio.fitsy.dto.request.CreateAlimentoRefeicaoRequest;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.service.alimentacao.AlimentoRefeicaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diario-alimentar")
public class AlimentacaoController {
    private final AlimentoRefeicaoService alimentoRefeicaoService;
    private final AlimentoRefeicaoMapper alimentoRefeicaoMapper;

    public AlimentacaoController(AlimentoRefeicaoService alimentoRefeicaoService, AlimentoRefeicaoMapper alimentoRefeicaoMapper) {
        this.alimentoRefeicaoMapper = alimentoRefeicaoMapper;
        this.alimentoRefeicaoService = alimentoRefeicaoService;

    }

    @PostMapping("/refeicao/alimento/add")
    public ResponseEntity<AlimentoRefeicaoResponse> createAlimentoRefeicao(@AuthenticationPrincipal User user, @RequestBody @Valid CreateAlimentoRefeicaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoRefeicaoMapper.modelToResponse(alimentoRefeicaoService.createAlimentoRefeicao(user, request)));
    }
}

