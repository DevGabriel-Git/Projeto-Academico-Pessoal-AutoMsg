package com.automsg.repositories;

import com.automsg.domains.Mecanica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MecanicaRepositories extends JpaRepository<Mecanica, Long> {
    Optional<Mecanica> findByEmail(String email);
    boolean existsByEmail(String email);
}
