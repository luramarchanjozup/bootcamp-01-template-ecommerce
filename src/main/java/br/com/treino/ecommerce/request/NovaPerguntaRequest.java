package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Pergunta;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.shared.UsuarioLogado;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaPerguntaRequest {

    private @NotBlank String mensagem;

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Pergunta toPergunta(Produto produto,UsuarioLogado interessado) { //1 //2
        return new Pergunta(this.mensagem, produto, interessado.get()); //3
    }

}
