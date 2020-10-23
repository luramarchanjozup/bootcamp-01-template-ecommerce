package com.zup.mercadolivre.categoria;

import com.zup.mercadolivre.compartilhado.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(this.nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
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
