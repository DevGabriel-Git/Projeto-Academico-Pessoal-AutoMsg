package com.automsg.services;

import com.automsg.repositories.MensagemRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class WhatsAppService {

    private static final Logger log = LoggerFactory.getLogger(WhatsAppService.class);

    private final String instanceId = System.getenv("ZAPI_INSTANCE_ID");
    private final String zapiToken  = System.getenv("ZAPI_TOKEN");

    @Autowired
    private MensagemRepositories mensagemRepo;

    public void enviar(String telefone, String mensagem) {
        if (instanceId == null || zapiToken == null) {
            log.info("[SIMULADO] Para: {} | Mensagem: {}", telefone, mensagem);
            return;
        }
        try {
            String url = String.format(
                    "https://api.z-api.io/instances/%s/token/%s/send-text",
                    instanceId, zapiToken
            );
            Map<String, String> body = Map.of(
                    "phone", telefone.replaceAll("[^0-9]", ""),
                    "message", mensagem
            );
            new RestTemplate().postForObject(url, body, String.class);
            log.info("WhatsApp enviado para {}", telefone);
        } catch (Exception e) {
            log.error(" Erro ao enviar WhatsApp para {}: {}", telefone, e.getMessage());
        }
    }

    public String montar(String template, String nome, String servico, String nomeOficina) {
        return template
                .replace("{nome}", nome)
                .replace("{servico}", servico)
                .replace("{negocio}", nomeOficina);
    }
}