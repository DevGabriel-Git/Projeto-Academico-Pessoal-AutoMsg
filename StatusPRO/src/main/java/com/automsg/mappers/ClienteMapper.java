package com.automsg.mappers;

import com.automsg.domains.Cliente;
import com.automsg.domains.dto.ClienteDTO.ClienteResponse;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class ClienteMapper {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ClienteResponse toResponse(Cliente c) {
        ClienteResponse r = new ClienteResponse();
        r.setId(c.getId());
        r.setNome(c.getNome());
        r.setTelefone(c.getTelefone());
        r.setVeiculo(c.getVeiculo());
        r.setPlaca(c.getPlaca());
        r.setObservacoes(c.getObservacoes());
        r.setTotalVisitas(c.getTotalVisitas());
        r.setUltimaVisita(c.getUltimaVisita() != null ? c.getUltimaVisita().format(FMT) : null);
        r.setCriadoEm(c.getCriadoEm() != null ? c.getCriadoEm().format(FMT) : null);
        return r;
    }
}
