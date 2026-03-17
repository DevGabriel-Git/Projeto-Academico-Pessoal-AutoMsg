package com.automsg.domains.dto;

import com.automsg.domains.enums.TipoServico;
import jakarta.validation.constraints.*;
import lombok.Data;

public class ServicoDTO {

    @Data
    public static class CriarRequest {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        private String descricao;
        private Double preco;
        private TipoServico tipo;
    }

    @Data
    public static class ServicoResponse {
        private Long id;
        private String nome;
        private String descricao;
        private Double preco;
        private String tipo;
    }
}