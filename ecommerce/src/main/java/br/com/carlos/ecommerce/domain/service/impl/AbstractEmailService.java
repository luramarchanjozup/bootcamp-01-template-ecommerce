package br.com.carlos.ecommerce.domain.service.impl;

import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.entity.Pergunta;
import br.com.carlos.ecommerce.domain.service.EmailService;
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
    @Override
    public void enviarEmailNovaCompra(Compra compra) {
        SimpleMailMessage sm = preparNovaCompraEmail(compra);
        enviarEmail(sm);
    }
    @Override
    public void enviarEmailFalhaProcessarCompra(Compra compra) {
        SimpleMailMessage sm = preparFalhaProcessoCompraEmail(compra);
        enviarEmail(sm);
    }

    protected SimpleMailMessage preparNovaPerguntaEmail(Pergunta pergunta) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pergunta.getInteressado().getLogin());
        sm.setFrom(sender);
        sm.setSubject("Nova pergunta para o produto id: "+ pergunta.getProduto().getId() + " - "
                + pergunta.getProduto().getNome());
        sm.setText(pergunta.getTitulo());
        return sm;
    }
    protected SimpleMailMessage preparNovaCompraEmail(Compra compra) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(compra.getComprador().getLogin());
        sm.setFrom(sender);
        sm.setSubject("Nova compra realizada do produto id: "+ compra.getProdutoEscolhido().getId()
                + " - " + compra.getProdutoEscolhido().getNome());
        sm.setText("Seu estoque atualizado do produto" + compra.getProdutoEscolhido().getNome() + " possui um total de "+ compra.getProdutoEscolhido().getQuantidade());
        return sm;
    }

    protected SimpleMailMessage preparFalhaProcessoCompraEmail(Compra compra) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(compra.getComprador().getLogin());
        sm.setFrom(sender);
        sm.setSubject("Falha ao realizar compra produto id: "+ compra.getProdutoEscolhido().getId()
                + " - " + compra.getProdutoEscolhido().getNome());
        sm.setText("Falha no processo final do produto" + compra.getProdutoEscolhido().getNome());
        return sm;
    }
}
