package br.com.ecommerce.adicionaresposta;

import br.com.ecommerce.fazerpergunta.Pergunta;

import javax.persistence.EntityManager;

public class RespostaRequest {

    private String conteudo;

    private Long perguntaId;

    public RespostaRequest(String conteudo, Long perguntaId) {
        this.conteudo = conteudo;
        this.perguntaId = perguntaId;
    }

    public Resposta converteParaTipoResposta(EntityManager entityManager){

        Pergunta pergunta = entityManager.find(Pergunta.class, perguntaId);

        return new Resposta(conteudo, pergunta);

    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getPerguntaId() {
        return perguntaId;
    }

    public void setPerguntaId(Long perguntaId) {
        this.perguntaId = perguntaId;
    }
}
