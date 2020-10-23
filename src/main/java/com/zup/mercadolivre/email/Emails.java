package com.zup.mercadolivre.email;

import com.zup.mercadolivre.fluxocompra.Compra;
import com.zup.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails {
    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
        mailer.send("Possível nova pergunta " + pergunta, "Nova pergunta", pergunta.getPerguntante().getEmail(),
                "perguntas@nossomercadolivre.com", pergunta.getDonoProduto().getEmail());
    }

    public void novaCompra(@NotNull @Valid Compra compra) {
        mailer.send("Possível nova compra " + compra, "Tem um cliente interessado no seu produto",
                compra.getComprador().getEmail(),"compras@nossomercadolivre.com",
                compra.getDonoProduto().getEmail());
    }

    public void compraConcluida(@NotNull @Valid Compra compra) {
        mailer.send("Compra concluída " + compra, "Sua compra foi concluída",
                compra.getDonoProduto().getEmail(),"compras@nossomercadolivre.com",
                compra.getComprador().getEmail());
    }

    public void compraFalhou(@NotNull @Valid Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        mailer.send("Compra falhou " + compra, "Tente novamente no link",
                compra.urlRedirecionamento(uriComponentsBuilder),"compras@nossomercadolivre.com",
                compra.getComprador().getEmail());
    }
}
