package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.MetaNutrientesMapper;
import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.response.MetaNutrientesResponse;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.service.metas.MetaNutrientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-info/meta-nutrientes")
public class MetaNutrientesController {

    private final MetaNutrientesService metaNutrientesService;
    private final MetaNutrientesMapper metaNutrientesMapper;

    public MetaNutrientesController(MetaNutrientesService metaNutrientesService, MetaNutrientesMapper metaNutrientesMapper) {
        this.metaNutrientesMapper = metaNutrientesMapper;
        this.metaNutrientesService = metaNutrientesService;
    }

    @PostMapping("/create")
    public ResponseEntity<MetaNutrientesResponse> createMetaNutrientes(@AuthenticationPrincipal User user, @RequestBody @Valid MetaNutrientesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metaNutrientesMapper.modelToResponse(metaNutrientesService.createForUser(user, request)));
    }

    @GetMapping("active")
    public ResponseEntity<MetaNutrientesResponse> getActiveMetaNutrientes(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(metaNutrientesMapper.modelToResponse(metaNutrientesService.getActiveMetaNutrientes(user)));
    }
}
