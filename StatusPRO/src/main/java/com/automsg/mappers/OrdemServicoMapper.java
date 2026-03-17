package com.automsg.mappers;

import com.automsg.domains.OrdemServico;
import com.automsg.domains.dto.OrdemServicoDTO.OrdemResponse;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class OrdemServicoMapper {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public OrdemResponse toResponse(OrdemServico os) {
        OrdemResponse r = new OrdemResponse();
        r.setId(os.getId());
        r.setServico(os.getServico());
        r.setDescricao(os.getDescricao());
        r.setStatus(os.getStatus().name());
        r.setNomeCliente(os.getCliente().getNome());
        r.setTelefoneCliente(os.getCliente().getTelefone());
        r.setVeiculoCliente(os.getCliente().getVeiculo());
        r.setPlacaCliente(os.getCliente().getPlaca());
        r.setCriadoEm(os.getCriadoEm() != null ? os.getCriadoEm().format(FMT) : null);
        r.setAtualizadoEm(os.getAtualizadoEm() != null ? os.getAtualizadoEm().format(FMT) : null);
        return r;
    }
}