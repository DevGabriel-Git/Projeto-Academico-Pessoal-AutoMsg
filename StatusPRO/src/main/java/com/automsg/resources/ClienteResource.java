package com.automsg.resources;

import com.automsg.domains.Mecanica;
import com.automsg.domains.dto.ClienteDTO.*;
import com.automsg.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar(@RequestParam Long mecanicaId) {
        return ResponseEntity.ok(clienteService.listar(mecanicaId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> buscar(@RequestParam Long mecanicaId,
                                                        @RequestParam String nome) {
        return ResponseEntity.ok(clienteService.buscar(mecanicaId, nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@RequestParam Long mecanicaId,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id, mecanicaId));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestParam Long mecanicaId,
                                                 @Valid @RequestBody CriarRequest req) {
        Mecanica mecanica = new Mecanica();
        mecanica.setId(mecanicaId);
        return ResponseEntity.ok(clienteService.criar(req, mecanica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@RequestParam Long mecanicaId,
                                                     @PathVariable Long id,
                                                     @RequestBody AtualizarRequest req) {
        return ResponseEntity.ok(clienteService.atualizar(id, mecanicaId, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestParam Long mecanicaId,
                                        @PathVariable Long id) {
        clienteService.deletar(id, mecanicaId);
        return ResponseEntity.noContent().build();
    }
}