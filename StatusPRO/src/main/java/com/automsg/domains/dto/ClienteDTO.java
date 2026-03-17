package com.automsg.domains.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class ClienteDTO {

    @Data
    public static class CriarRequest{
        @NotBlank( message= "Nome é obrigatório")
        private String nome;

        @NotBlank(message = "Telefone é obrigatório")
        private String telefone;

        private String veiculo;
        private String placa;
        private String observacoes;

    }

    @Data
    public static class AtualizarRequest{
        private String nome;
        private String telefone;
        private String veiculo;
        private String placa;
        private String observacoes;
    }

    @Data
    public static class ClienteResponse {
        private Long id;
        private String nome;
        private String telefone;
        private String veiculo;
        private String placa;
        private String observacoes;
        private int totalVisitas;
        private String ultimaVisita;
        private String criadoEm;
    }
}
