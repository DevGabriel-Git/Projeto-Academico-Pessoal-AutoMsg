package com.automsg.domains;

import com.automsg.domains.enums.PerfilUsuario;
import com.automsg.domains.enums.PlanoMecanica;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "mecanicas")
@Data
public class Mecanica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String telefone;
    private String endereco;

    @Column(length = 500)
    private String msgEmAndamento = "Olá {nome}! Seu {servico} está em andamento";

    @Column(length = 500)
    private String msgPronto = "Olá {nome}! Seu {servico} está PRONTO! Pode vir buscar";

    @Enumerated(EnumType.STRING)
    private PlanoMecanica plano = PlanoMecanica.BASICO;

    private int mensagensUsadas = 0;
    private int limiteMensagens = 200;

    private LocalDateTime criadoEm = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil = PerfilUsuario.ADMIN;
}