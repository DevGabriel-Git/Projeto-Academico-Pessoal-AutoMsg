package com.automsg.repositories;

import com.automsg.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositories extends JpaRepository<Cliente, Long> {
    List<Cliente> findByMecanicaId(Long mecanicaId);
    List<Cliente> findByMecanicaIdAndNomeContainingIgnoreCase(Long mecanicaId, String nome);
    Optional<Cliente> findByIdAndMecanicaId(Long id, Long mecanicaId);
}