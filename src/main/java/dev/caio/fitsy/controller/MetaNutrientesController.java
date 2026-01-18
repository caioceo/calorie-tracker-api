package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.request.MetaNutrientesRequest;
import dev.caio.fitsy.dto.response.MetaNutrientesResponse;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.service.MetaNutrientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-info/meta-nutrientes")
public class MetaNutrientesController {

    private MetaNutrientesService metaNutrientesService;

    public MetaNutrientesController(MetaNutrientesService metaNutrientesService) {
        this.metaNutrientesService = metaNutrientesService;
    }

    @PostMapping("/create")
    public ResponseEntity<MetaNutrientesResponse> createMetaNutrientes(@AuthenticationPrincipal User user, @RequestBody @Valid MetaNutrientesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metaNutrientesService.createMetaNutrientes(user, request));
    }

    @GetMapping("active")
    public ResponseEntity<MetaNutrientesResponse> getActiveMetaNutrientes(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(metaNutrientesService.getActiveMetaNutrientes(user));
    }
}
