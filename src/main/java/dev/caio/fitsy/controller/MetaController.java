package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.request.UpdateMetaRequest;
import dev.caio.fitsy.dto.response.HistoricoMetaReponse;
import dev.caio.fitsy.dto.response.MetaResponse;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.service.MetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-info/meta")
public class MetaController {
    
    private final MetaService metaService;

    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

    @PostMapping("/create")
    public ResponseEntity<MetaResponse> createNewMeta(@AuthenticationPrincipal User user, @RequestBody @Valid CreateMetaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(metaService.createNewMeta(user, request));
    }

    @PutMapping("/active/update")
    public ResponseEntity<MetaResponse> updateMeta(@AuthenticationPrincipal User user, @RequestBody @Valid UpdateMetaRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(metaService.updateActiveMeta(user, request));
    }

    @GetMapping("/get-active")
    public ResponseEntity<MetaResponse> getActiveMeta(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(metaService.getActiveMeta(user));
    }

    @GetMapping("")
    public ResponseEntity<List<HistoricoMetaReponse>> getAllMeta(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(metaService.getHistoricoMetas(user));
    }
}
