package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Opiniao;

public class SiteOpiniaoResponse {

    private int nota;

    private String titulo;

    private String descricao;

    public SiteOpiniaoResponse(Opiniao opiniao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
