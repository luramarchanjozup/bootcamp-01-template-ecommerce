package br.com.carlos.ecommerce.domain.service;

import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.entity.Pergunta;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void enviarEmail(SimpleMailMessage mensagem);

    void enviarEmailNovaPergunta(Pergunta pergunta);
    void enviarEmailNovaCompra(Compra compra);
    void enviarEmailFalhaProcessarCompra(Compra compra);
}
