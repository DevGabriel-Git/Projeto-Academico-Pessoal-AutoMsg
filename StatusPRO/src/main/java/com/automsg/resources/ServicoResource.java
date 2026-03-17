package com.automsg.resources;

import com.automsg.domains.dto.ServicoDTO.*;
import com.automsg.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

    @Autowired private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listar(@RequestParam Long mecanicaId) {
        return ResponseEntity.ok(servicoService.listar(mecanicaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> buscarPorId(@RequestParam Long mecanicaId,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id, mecanicaId));
    }

    @PostMapping
    public ResponseEntity<ServicoResponse> criar(@RequestParam Long mecanicaId,
                                                 @Valid @RequestBody CriarRequest req) {
        return ResponseEntity.ok(servicoService.criar(req, mecanicaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestParam Long mecanicaId,
                                        @PathVariable Long id) {
        servicoService.deletar(id, mecanicaId);
        return ResponseEntity.noContent().build();
    }
}