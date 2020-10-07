package com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class OpiniaoDTO {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(columnDefinition = "text")
    private String descricao;

    public Opiniao toModel(EntityManager entityManager, String emailDoUsuario){
        Usuario usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", emailDoUsuario)
                .getSingleResult();

        return new Opiniao(nota, titulo, descricao, usuario);
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
