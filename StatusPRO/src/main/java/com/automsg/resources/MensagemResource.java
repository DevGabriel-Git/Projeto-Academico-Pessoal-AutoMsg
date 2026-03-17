package com.automsg.resources;

import com.automsg.domains.dto.MensagemDTO.*;
import com.automsg.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemResource {

    @Autowired private MensagemService mensagemService;

    @GetMapping
    public ResponseEntity<List<MensagemResponse>> listar(@RequestParam Long mecanicaId) {
        return ResponseEntity.ok(mensagemService.listar(mecanicaId));
    }

    @GetMapping("/ordem/{ordemId}")
    public ResponseEntity<List<MensagemResponse>> listarPorOrdem(@PathVariable Long ordemId) {
        return ResponseEntity.ok(mensagemService.listarPorOrdem(ordemId));
    }
}