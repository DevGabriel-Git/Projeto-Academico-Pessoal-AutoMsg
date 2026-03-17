package com.automsg.services;

import com.automsg.domains.Mecanica;
import com.automsg.domains.Servico;
import com.automsg.domains.dto.ServicoDTO.*;
import com.automsg.repositories.MecanicaRepositories;
import com.automsg.repositories.ServicoRepositories;
import com.automsg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired private ServicoRepositories servicoRepo;
    @Autowired private MecanicaRepositories mecanicaRepo;

    public ServicoResponse criar(CriarRequest req, Long mecanicaId) {
        Mecanica mecanica = mecanicaRepo.findById(mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mecânica não encontrada"));

        Servico s = new Servico();
        s.setNome(req.getNome());
        s.setDescricao(req.getDescricao());
        s.setPreco(req.getPreco());
        s.setTipo(req.getTipo());
        s.setMecanica(mecanica);
        return toResponse(servicoRepo.save(s));
    }

    public List<ServicoResponse> listar(Long mecanicaId) {
        return servicoRepo.findByMecanicaId(mecanicaId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ServicoResponse buscarPorId(Long id, Long mecanicaId) {
        return servicoRepo.findByIdAndMecanicaId(id, mecanicaId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));
    }

    public void deletar(Long id, Long mecanicaId) {
        Servico s = servicoRepo.findByIdAndMecanicaId(id, mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));
        servicoRepo.delete(s);
    }

    private ServicoResponse toResponse(Servico s) {
        ServicoResponse r = new ServicoResponse();
        r.setId(s.getId());
        r.setNome(s.getNome());
        r.setDescricao(s.getDescricao());
        r.setPreco(s.getPreco());
        r.setTipo(s.getTipo() != null ? s.getTipo().name() : null);
        return r;
    }
}