package com.automsg.resources;

import com.automsg.domains.Mecanica;
import com.automsg.domains.dto.OrdemServicoDTO.*;
import com.automsg.services.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoResource {

    @Autowired private OrdemServicoService ordemService;

    @GetMapping
    public ResponseEntity<List<OrdemResponse>> listar(@RequestParam Long mecanicaId) {
        return ResponseEntity.ok(ordemService.listar(mecanicaId));
    }

    @GetMapping("/resumo")
    public ResponseEntity<Map<String, Object>> resumo(@RequestParam Long mecanicaId) {
        return ResponseEntity.ok(ordemService.resumo(mecanicaId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdemResponse>> listarPorStatus(@RequestParam Long mecanicaId,
                                                               @PathVariable String status) {
        return ResponseEntity.ok(ordemService.listarPorStatus(mecanicaId, status));
    }

    @PostMapping
    public ResponseEntity<OrdemResponse> criar(@RequestParam Long mecanicaId,
                                               @Valid @RequestBody CriarRequest req) {
        Mecanica mecanica = new Mecanica();
        mecanica.setId(mecanicaId);
        return ResponseEntity.ok(ordemService.criar(req, mecanica));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrdemResponse> atualizarStatus(@RequestParam Long mecanicaId,
                                                         @PathVariable Long id,
                                                         @RequestBody AtualizarStatusRequest req) {
        return ResponseEntity.ok(ordemService.atualizarStatus(id, mecanicaId, req.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestParam Long mecanicaId,
                                        @PathVariable Long id) {
        ordemService.deletar(id, mecanicaId);
        return ResponseEntity.noContent().build();
    }
}