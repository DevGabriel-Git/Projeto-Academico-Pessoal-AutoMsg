package com.automsg.domains.dto;

import lombok.Data;

public class MensagemDTO {

    @Data
    public static class MensagemResponse {
        private Long id;
        private String texto;
        private String telefoneDestino;
        private boolean enviado;
        private String enviadoEm;
        private Long ordemId;
    }
}