package br.com.ecommerce.fazerpergunta;

import br.com.ecommerce.adicionaresposta.Resposta;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private OffsetDateTime instanteCriacao = OffsetDateTime.now();

    /* @complexidade = acoplamento contextual */
    @ManyToOne
    private Usuario usuario;

    /* @complexidade = acoplamento contextual */
    @ManyToOne
    private Produto produto;

    /* @complexidade = acoplamento contextual */
    @Size(max=1)
    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> resposta;

    @Deprecated
    public Pergunta(){};

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

}
