package br.com.zup.ecommerce.service.produto;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.service.email.EnviarEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Component
public class EnvioPergunta {

    @Autowired
    //1
    private EnviarEmail enviarEmail;

    //1
    public void enviaEmail(Produto produto, String pergunta) {
        String assunto = this.montarAssuntoEmail(produto);
        String mensagem = this.montarCorpoEmail(produto, pergunta);
        enviarEmail.enviarEmail(produto.getDono().getLogin(), assunto, mensagem);
    }

    public String montarAssuntoEmail(Produto produto) {
        return String.format(
                "Pergunta sobre o produto %d - %s",
                produto.getId(), produto.getNome());
    }

    public String montarCorpoEmail(Produto produto, String pergunta){

        String host = ServletUriComponentsBuilder.fromCurrentServletMapping().toUriString();
        String linkProduto = String.format("%s/produtos/%d", host, produto.getId());

        return String.format(
                "Pergunta sobre o produto %d - %s\n\n" +
                        "Pergunta: %s\n\n" +
                        "Link do produto: %s",
                produto.getId(), produto.getNome(),
                pergunta,
                linkProduto);
    }
}
