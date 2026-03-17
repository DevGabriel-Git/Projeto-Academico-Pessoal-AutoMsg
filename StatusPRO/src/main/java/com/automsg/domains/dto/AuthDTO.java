package com.automsg.domains.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

public class AuthDTO {

    @Data
    public static class CadastroRequest {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        private String email;

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        private String senha;

        private String telefone;
        private String endereco;
    }

    @Data
    public static class LoginRequest {
        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String senha;
    }

    @Data
    public static class TokenResponse {
        private String token;
        private String nome;
        private String email;

        public TokenResponse(String token, String nome, String email) {
            this.token = token;
            this.nome = nome;
            this.email = email;
        }
    }
}