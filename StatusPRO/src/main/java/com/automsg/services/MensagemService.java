package com.automsg.services;

import com.automsg.domains.Mensagem;
import com.automsg.domains.dto.MensagemDTO.*;
import com.automsg.repositories.MensagemRepositories;
import com.automsg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensagemService {

    @Autowired private MensagemRepositories mensagemRepo;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public List<MensagemResponse> listar(Long mecanicaId) {
        return mensagemRepo.findByMecanicaId(mecanicaId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<MensagemResponse> listarPorOrdem(Long ordemId) {
        return mensagemRepo.findByOrdemServicoId(ordemId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private MensagemResponse toResponse(Mensagem m) {
        MensagemResponse r = new MensagemResponse();
        r.setId(m.getId());
        r.setTexto(m.getTexto());
        r.setTelefoneDestino(m.getTelefoneDestino());
        r.setEnviado(m.isEnviado());
        r.setEnviadoEm(m.getEnviadoEm() != null ? m.getEnviadoEm().format(FMT) : null);
        r.setOrdemId(m.getOrdemServico().getId());
        return r;
    }
}