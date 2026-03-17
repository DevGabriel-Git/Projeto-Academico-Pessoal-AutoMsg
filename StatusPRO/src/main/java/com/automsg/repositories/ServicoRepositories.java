package com.automsg.repositories;

import com.automsg.domains.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServicoRepositories extends JpaRepository<Servico, Long> {
    List<Servico> findByMecanicaId(Long mecanicaId);
    Optional<Servico> findByIdAndMecanicaId(Long id, Long mecanicaId);
}