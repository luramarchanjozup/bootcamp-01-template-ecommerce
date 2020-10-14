package com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Deprecated
    public OpiniaoDTO() {
    }

                        //1
    public OpiniaoDTO(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Opiniao toModel(EntityManager entityManager, String emailDoUsuario){
        //2
        Usuario usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", emailDoUsuario)
                .getSingleResult();

        //3
        Assert.notNull(usuario, "O usuário deve ser válido");

        return new Opiniao(nota, titulo, descricao, usuario);
    }

    public static List<OpiniaoDTO> listaDeOpinioesToDTO(List<Opiniao> opinioes){
                                    //3
        return opinioes.stream().map(OpiniaoDTO::new).collect(Collectors.toList());
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
