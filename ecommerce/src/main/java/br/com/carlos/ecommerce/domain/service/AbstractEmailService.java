package br.com.carlos.ecommerce.domain.service;

import br.com.carlos.ecommerce.domain.entity.Pergunta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarEmailNovaPergunta(Pergunta pergunta) {
        SimpleMailMessage sm = preparNovaPerguntaEmail(pergunta);
        enviarEmail(sm);
    }

    protected SimpleMailMessage preparNovaPerguntaEmail(Pergunta pergunta) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getInteressado().getLogin());
        sm.setFrom(sender);
        sm.setSubject("Nova pergunta para o produto id: "+ pergunta.getProduto().getId() + " - " + pergunta.getProduto().getNome());
        sm.setText(pergunta.getTitulo());
        return sm;
    }
}
