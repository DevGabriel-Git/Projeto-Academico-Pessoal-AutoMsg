package com.automsg.domains;

import com.automsg.domains.enums.TipoServico;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicos")
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private Double preco;

    @Enumerated(EnumType.STRING)
    private TipoServico tipo;

    private LocalDateTime criadoEm = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanica_id", nullable = false)
    private Mecanica mecanica;
}