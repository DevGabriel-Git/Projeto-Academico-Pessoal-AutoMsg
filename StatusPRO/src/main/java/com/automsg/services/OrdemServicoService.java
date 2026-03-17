package com.automsg.services;

import com.automsg.domains.Cliente;
import com.automsg.domains.Mecanica;
import com.automsg.domains.OrdemServico;
import com.automsg.domains.dto.OrdemServicoDTO.*;
import com.automsg.domains.enums.StatusOrdem;
import com.automsg.mappers.OrdemServicoMapper;
import com.automsg.repositories.ClienteRepositories;
import com.automsg.repositories.MecanicaRepositories;
import com.automsg.repositories.OrdemServicoRepositories;
import com.automsg.services.exceptions.BusinessException;
import com.automsg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    @Autowired private OrdemServicoRepositories ordemRepo;
    @Autowired private ClienteRepositories clienteRepo;
    @Autowired private MecanicaRepositories mecanicaRepo;
    @Autowired private WhatsAppService whatsAppService;
    @Autowired private OrdemServicoMapper ordemMapper;

    public OrdemResponse criar(CriarRequest req, Mecanica mecanica) {
        Mecanica mecanicaManaged = mecanicaRepo.findById(mecanica.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mecânica não encontrada"));

        Cliente cliente = clienteRepo
                .findByMecanicaIdAndNomeContainingIgnoreCase(mecanicaManaged.getId(), req.getNomeCliente())
                .stream().findFirst()
                .orElseGet(() -> {
                    Cliente novo = new Cliente();
                    novo.setNome(req.getNomeCliente());
                    novo.setTelefone(req.getTelefoneCliente());
                    novo.setVeiculo(req.getVeiculoCliente());
                    novo.setPlaca(req.getPlacaCliente());
                    novo.setMecanica(mecanicaManaged);
                    return clienteRepo.save(novo);
                });

        OrdemServico os = new OrdemServico();
        os.setServico(req.getServico());
        os.setDescricao(req.getDescricao());
        os.setStatus(StatusOrdem.AGUARDANDO);
        os.setCliente(cliente);
        os.setMecanica(mecanicaManaged);
        return ordemMapper.toResponse(ordemRepo.save(os));
    }

    public List<OrdemResponse> listar(Long mecanicaId) {
        return ordemRepo.findByMecanicaIdOrderByCriadoEmDesc(mecanicaId)
                .stream().map(ordemMapper::toResponse).collect(Collectors.toList());
    }

    public List<OrdemResponse> listarPorStatus(Long mecanicaId, String status) {
        try {
            StatusOrdem s = StatusOrdem.valueOf(status.toUpperCase());
            return ordemRepo.findByMecanicaIdAndStatus(mecanicaId, s)
                    .stream().map(ordemMapper::toResponse).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Status inválido. Use: AGUARDANDO, EM_ANDAMENTO ou PRONTO");
        }
    }

    public OrdemResponse atualizarStatus(Long ordemId, Long mecanicaId, String novoStatus) {
        OrdemServico os = ordemRepo.findByIdAndMecanicaId(ordemId, mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de serviço não encontrada"));

        StatusOrdem status;
        try {
            status = StatusOrdem.valueOf(novoStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Status inválido. Use: AGUARDANDO, EM_ANDAMENTO ou PRONTO");
        }

        Mecanica mecanica = os.getMecanica();
        os.setStatus(status);
        os.setAtualizadoEm(LocalDateTime.now());

        if (status == StatusOrdem.PRONTO) {
            Cliente cliente = os.getCliente();
            cliente.setTotalVisitas(cliente.getTotalVisitas() + 1);
            cliente.setUltimaVisita(LocalDateTime.now());
            clienteRepo.save(cliente);
        }

        String template = null;
        if (status == StatusOrdem.EM_ANDAMENTO) template = mecanica.getMsgEmAndamento();
        if (status == StatusOrdem.PRONTO)       template = mecanica.getMsgPronto();

        if (template != null) {
            String msg = whatsAppService.montar(
                    template,
                    os.getCliente().getNome(),
                    os.getServico(),
                    mecanica.getNome()
            );
            whatsAppService.enviar(os.getCliente().getTelefone(), msg);
            mecanica.setMensagensUsadas(mecanica.getMensagensUsadas() + 1);
            mecanicaRepo.save(mecanica);
        }

        return ordemMapper.toResponse(ordemRepo.save(os));
    }

    public void deletar(Long ordemId, Long mecanicaId) {
        OrdemServico os = ordemRepo.findByIdAndMecanicaId(ordemId, mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de serviço não encontrada"));
        ordemRepo.delete(os);
    }

    public Map<String, Object> resumo(Long mecanicaId) {
        long aguardando  = ordemRepo.countByMecanicaIdAndStatus(mecanicaId, StatusOrdem.AGUARDANDO);
        long emAndamento = ordemRepo.countByMecanicaIdAndStatus(mecanicaId, StatusOrdem.EM_ANDAMENTO);
        long pronto      = ordemRepo.countByMecanicaIdAndStatus(mecanicaId, StatusOrdem.PRONTO);
        Mecanica m = mecanicaRepo.findById(mecanicaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mecânica não encontrada"));
        return Map.of(
                "aguardando",      aguardando,
                "emAndamento",     emAndamento,
                "pronto",          pronto,
                "mensagensUsadas", m.getMensagensUsadas(),
                "limiteMensagens", m.getLimiteMensagens()
        );
    }
}