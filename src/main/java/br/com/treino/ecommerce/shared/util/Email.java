package br.com.treino.ecommerce.shared.util;

import br.com.treino.ecommerce.model.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Email {

    @Autowired
    @Qualifier("fakeMailer")
    private Mailer mailer;

    public void enviarPergunta(Pergunta novaPergunta) {

        mailer.enviar(novaPergunta.getInteressado().getEmail(),
                "perguntasecommerce@email.com",
                novaPergunta.getDonoProduto().getEmail(), novaPergunta.getMensagem());
    }

}
