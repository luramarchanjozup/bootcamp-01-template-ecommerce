package br.com.ecommerce.adicionaresposta;

import br.com.ecommerce.fazerpergunta.Pergunta;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudo;

    @ManyToOne
    private Pergunta pergunta;

    public Resposta(String conteudo, Pergunta pergunta) {
        this.conteudo = conteudo;
        this.pergunta = pergunta;
    }

}
