package com.automsg.repositories;

import com.automsg.domains.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensagemRepositories extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByMecanicaId(Long mecanicaId);
    List<Mensagem> findByOrdemServicoId(Long ordemId);
}