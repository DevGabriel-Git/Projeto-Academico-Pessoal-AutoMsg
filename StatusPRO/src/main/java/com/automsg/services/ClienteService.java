package com.automsg.services;

import com.automsg.domains.Cliente;
import com.automsg.domains.Mecanica;
import com.automsg.domains.dto.ClienteDTO.*;
import com.automsg.mappers.ClienteMapper;
import com.automsg.repositories.ClienteRepositories;
import com.automsg.repositories.MecanicaRepositories;
import com.automsg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired private ClienteRepositories clienteRepo;
    @Autowired private MecanicaRepositories mecanicaRepo;
    @Autowired private ClienteMapper clienteMapper;

    public ClienteResponse criar(CriarRequest req, Mecanica mecanica) {
        Mecanica mecanicaManaged = mecanicaRepo.findById(mecanica.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mecânica não encontrada"));

        Cliente c = new Cliente();
        c.setNome(req.getNome());
        c.setTelefone(req.getTelefone());
        c.setVeiculo(req.getVeiculo());
        c.setPlaca(req.getPlaca());
        c.setObservacoes(req.getObservacoes());
        c.setMecanica(mecanicaManaged);
        return clienteMapper.toResponse(clienteRepo.save(c));
    }

    public List<ClienteResponse> listar(Long mecanicaId) {
        return clienteRepo.findByMecanicaId(mecanicaId)
                .stream().map(clienteMapper::toResponse).collect(Collectors.toList());
    }

    public List<ClienteResponse> buscar(Long mecanicaId, String nome) {
        return clienteRepo.findByMecanicaIdAndNomeContainingIgnoreCase(mecanicaId, nome)
                .stream().map(clienteMapper::toResponse).collect(Collectors.toList());
    }

    public ClienteResponse buscarPorId(Long id, Long mecanicaId) {
        return clienteRepo.findByIdAndMecanicaId(id, mecanicaId)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public ClienteResponse atualizar(Long id, Long mecanicaId, AtualizarRequest req) {
        Cliente c = clienteRepo.findByIdAndMecanicaId(id, mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        if (req.getNome() != null)        c.setNome(req.getNome());
        if (req.getTelefone() != null)    c.setTelefone(req.getTelefone());
        if (req.getVeiculo() != null)     c.setVeiculo(req.getVeiculo());
        if (req.getPlaca() != null)       c.setPlaca(req.getPlaca());
        if (req.getObservacoes() != null) c.setObservacoes(req.getObservacoes());
        return clienteMapper.toResponse(clienteRepo.save(c));
    }

    public void deletar(Long id, Long mecanicaId) {
        Cliente c = clienteRepo.findByIdAndMecanicaId(id, mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        clienteRepo.delete(c);
    }
}