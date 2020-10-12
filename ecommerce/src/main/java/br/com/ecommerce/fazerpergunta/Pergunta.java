package br.com.ecommerce.fazerpergunta;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private OffsetDateTime instanteCricao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.instanteCricao = OffsetDateTime.now();
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pergunta pergunta = (Pergunta) o;

        return id.equals(pergunta.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public OffsetDateTime getInstanteCricao() {
        return instanteCricao;
    }

    public void setInstanteCricao(OffsetDateTime instanteCricao) {
        this.instanteCricao = instanteCricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
