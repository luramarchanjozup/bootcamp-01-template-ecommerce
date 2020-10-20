package io.github.evertoncnsouza.domain.service;

import io.github.evertoncnsouza.domain.entity.Compra;
import io.github.evertoncnsouza.domain.entity.Pergunta;
import io.github.evertoncnsouza.domain.repository.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//2 PCI's
@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    public void pergunta(@Valid @NotNull Pergunta pergunta) {

        mailer.send("<html>...</html>","Você tem uma pergunta...",pergunta.getNavegador().getEmail(),
                "novapergunta@nossomercadolivre.com",pergunta.getDono().getEmail());
    }

    public void novaCompra(Compra novaCompra) {
        mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
                novaCompra.getNavegador().getEmail(),
                "compras@nossomercadolivre.com",
                novaCompra.getDonoProduto().getEmail());
    }
}



