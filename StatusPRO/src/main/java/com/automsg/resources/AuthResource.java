package com.automsg.resources;

import com.automsg.domains.dto.AuthDTO.*;
import com.automsg.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired private AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<TokenResponse> cadastrar(@Valid @RequestBody CadastroRequest req) {
        return ResponseEntity.ok(authService.cadastrar(req));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
}