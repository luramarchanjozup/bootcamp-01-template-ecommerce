package com.github.marcoscoutozup.ecommerce.produto;

import com.github.marcoscoutozup.ecommerce.caracteristica.Caracteristica;
import com.github.marcoscoutozup.ecommerce.categoria.Categoria;
import com.github.marcoscoutozup.ecommerce.produto.adicionaropiniao.Opiniao;
import com.github.marcoscoutozup.ecommerce.produto.adicionarpergunta.Pergunta;
import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ElementCollection
    private List<Caracteristica> caracteristicas;

    @NotBlank
    @Size(max = 1000)
    @Column(columnDefinition =  "text")
    private String descricao;

    @NotNull
    @ManyToOne //1
    private Categoria categoria;

    @NotNull
    @ManyToOne //2
    private Usuario usuario;

    @ElementCollection
    private List<String> imagens;

    @ElementCollection //3
    private List<Opiniao> opinioes;

    @ElementCollection //4
    private List<Pergunta> perguntas;

    @CreationTimestamp
    private LocalDateTime created_at;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @PositiveOrZero Integer quantidade, List<Caracteristica> caracteristicas, @NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull Usuario usuario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public boolean verificarSeEOProprietarioDoProduto(String email){
        return usuario.getEmail().equals(email);
    }

    public void adicionarListaDeImagensNoProduto(List<String> imagens){
        this.imagens.addAll(imagens);
    }

    public void adicionarOpiniaoAoProduto(Opiniao opiniao){
        this.opinioes.add(opiniao);
    }

    public void adicionarPerguntaAoProduto(Pergunta pergunta){
        this.perguntas.add(pergunta);
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public String prepararPerguntaDoProdutoParaEmail(){
        return "\n\n*** Olá, você tem uma nova pergunta sobre o produto "+ nome +" ***" +
                "\n\n" + perguntas.get(perguntas.size()-1).prepararPerguntaParaEmail() +
                "\n\nLink do Produto: " + "\n\n";
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", imagens=" + imagens +
                ", opinioes=" + opinioes +
                ", perguntas=" + perguntas +
                '}';
    }
}
