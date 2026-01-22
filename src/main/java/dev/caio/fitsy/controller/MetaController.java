package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.MetaMapper;
import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.request.UpdateMetaRequest;
import dev.caio.fitsy.dto.response.HistoricoMetaReponse;
import dev.caio.fitsy.dto.response.MetaResponse;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.service.metas.MetaService;
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
    private final MetaMapper metaMapper;

    public MetaController(MetaService metaService, MetaMapper metaMapper) {
        this.metaService = metaService;
        this.metaMapper = metaMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<MetaResponse> createNewMeta(@AuthenticationPrincipal User user, @RequestBody @Valid CreateMetaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(metaMapper.modelToResponse(HttpStatus.CREATED.value(),metaService.createNewMeta(user, request)));
    }

    @PutMapping("/active/update")
    public ResponseEntity<MetaResponse> updateMeta(@AuthenticationPrincipal User user, @RequestBody @Valid UpdateMetaRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(metaMapper.modelToResponse(HttpStatus.OK.value(), metaService.updateActiveMeta(user, request)));
    }

    @GetMapping("/active")
    public ResponseEntity<MetaResponse> getActiveMeta(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(metaMapper.modelToResponse(HttpStatus.OK.value(), metaService.getActiveMeta(user)));
    }

    @GetMapping("")
    public ResponseEntity<List<HistoricoMetaReponse>> getAllMeta(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(metaMapper.modelListToResponseList(metaService.getHistoricoMetas(user)));
    }
}
