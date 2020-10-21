package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Categoria;

//1 PCI;
public class SiteCategoriaResponse {

    private String nome;
    private Categoria idCategoriaMae;


    public SiteCategoriaResponse(Categoria categoria) {
        nome = categoria.getNome();
        idCategoriaMae = categoria.getCategoriaMae();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Categoria idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}