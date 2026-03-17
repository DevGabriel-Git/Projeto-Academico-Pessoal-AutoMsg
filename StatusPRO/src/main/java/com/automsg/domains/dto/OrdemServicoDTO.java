package com.automsg.domains.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

public class OrdemServicoDTO {

    @Data
    public static class CriarRequest {
        @NotBlank(message = "Nome do cliente é obrigatório")
        private String nomeCliente;

        @NotBlank(message = "Telefone do cliente é obrigatório")
        private String telefoneCliente;

        private String veiculoCliente;
        private String placaCliente;

        @NotBlank(message = "Serviço é obrigatório")
        private String servico;

        private String descricao;
    }

    @Data
    public static class AtualizarStatusRequest {
        @NotBlank(message = "Status é obrigatório")
        private String status;
    }

    @Data
    public static class OrdemResponse {
        private Long id;
        private String servico;
        private String descricao;
        private String status;
        private String nomeCliente;
        private String telefoneCliente;
        private String veiculoCliente;
        private String placaCliente;
        private String criadoEm;
        private String atualizadoEm;
    }
}