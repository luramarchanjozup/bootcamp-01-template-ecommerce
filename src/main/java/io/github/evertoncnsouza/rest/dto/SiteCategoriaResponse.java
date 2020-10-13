package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Categoria;

public class SiteCategoriaResponse {

    private String nome;

    private Long idCategoriaMae;

    public SiteCategoriaResponse(Categoria categoria) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}
