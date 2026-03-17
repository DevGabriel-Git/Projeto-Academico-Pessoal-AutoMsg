package com.automsg.services;

import com.automsg.domains.Mecanica;
import com.automsg.domains.dto.AuthDTO.*;
import com.automsg.infra.JwtService;
import com.automsg.repositories.MecanicaRepositories;
import com.automsg.services.exceptions.BusinessException;
import com.automsg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private MecanicaRepositories mecanicaRepo;
    @Autowired private JwtService jwtService;

    public TokenResponse cadastrar(CadastroRequest req) {
        if (mecanicaRepo.existsByEmail(req.getEmail())) {
            throw new BusinessException("E-mail já cadastrado");
        }
        Mecanica m = new Mecanica();
        m.setNome(req.getNome());
        m.setEmail(req.getEmail());
        m.setSenha("123456");
        m.setTelefone(req.getTelefone());
        m.setEndereco(req.getEndereco());

        Mecanica salva = mecanicaRepo.save(m);
        String token = jwtService.gerarToken(salva.getId(), salva.getEmail());
        return new TokenResponse(token, salva.getNome(), salva.getEmail());
    }

    public TokenResponse login(LoginRequest req) {
        Mecanica m = mecanicaRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("E-mail não encontrado"));

        String token = jwtService.gerarToken(m.getId(), m.getEmail());
        return new TokenResponse(token, m.getNome(), m.getEmail());
    }
}