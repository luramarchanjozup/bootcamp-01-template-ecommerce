package com.github.marcoscoutozup.ecommerce.produto.adicionarpergunta;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class PerguntaDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @Deprecated
    public PerguntaDTO() {
    }
                        //1
    public PerguntaDTO(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.pergunta = pergunta.getPergunta();
    }

    public Pergunta toModel(EntityManager entityManager, String emailDoUsuario) {
        //2
        Usuario usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", emailDoUsuario)
                .getSingleResult();

        Assert.notNull(usuario, "O usuário deve ser válido");

        return new Pergunta(titulo, pergunta, usuario);
    }

    public static List<PerguntaDTO> listaDePerguntasToDTO(List<Pergunta> perguntas){
                                        //4
        return perguntas.stream().map(PerguntaDTO::new).collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

}
