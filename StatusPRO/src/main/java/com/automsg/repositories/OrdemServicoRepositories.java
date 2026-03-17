package com.automsg.repositories;

import com.automsg.domains.OrdemServico;
import com.automsg.domains.enums.StatusOrdem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepositories extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByMecanicaIdOrderByCriadoEmDesc(Long mecanicaId);
    List<OrdemServico> findByMecanicaIdAndStatus(Long mecanicaId, StatusOrdem status);
    List<OrdemServico> findByClienteIdAndMecanicaId(Long clienteId, Long mecanicaId);
    Optional<OrdemServico> findByIdAndMecanicaId(Long id, Long mecanicaId);
    long countByMecanicaIdAndStatus(Long mecanicaId, StatusOrdem status);
}
