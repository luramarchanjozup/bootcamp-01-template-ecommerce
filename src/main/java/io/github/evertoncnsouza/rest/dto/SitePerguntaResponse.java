package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Pergunta;

public class SitePerguntaResponse {

    private String titulo;

    public SitePerguntaResponse(Pergunta pergunta) {
        this.titulo = titulo;
    }

    public String getPergunta() {
        return titulo;
    }
}


