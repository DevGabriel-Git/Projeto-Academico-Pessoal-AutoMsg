package com.automsg.domains;

import com.automsg.domains.enums.StatusOrdem;
import com.automsg.domains.enums.TipoServico;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordens_servico")
@Data
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String servico;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusOrdem status = StatusOrdem.AGUARDANDO;

    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanica_id", nullable = false)
    private Mecanica mecanica;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
}