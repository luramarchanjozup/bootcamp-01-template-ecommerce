package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.fazerpergunta.Pergunta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Long quantidadeDisponivel;

    @OneToMany(mappedBy = "produto")
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<ImagemProduto> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @ManyToOne
    private Categoria categoria;

    private OffsetDateTime instanteCadastro;

    @Deprecated
    public Produto(){};

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidadeDisponivel,
                   List<Caracteristica> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
                   Categoria categoria) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = OffsetDateTime.now();

    }

    public List<String> listarLinks(Function<ImagemProduto, String> funcaoDeListagem){

        return this.imagens
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }

    public List<String> listarCaracteristicas(Function<Caracteristica, String> funcaoDeListagem){

        return this.caracteristicas
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }

    public List<String> listarOpinioes(Function<Opiniao, String> funcaoDeListagem){

        return this.opinioes
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }

    public List<String> listarPerguntas(Function<Pergunta, String> funcaoDeListagem){

        return this.perguntas
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }


    public List<Opiniao> getOpinioes() {
        return opinioes;
    }


    public String getNome() {
        return nome;
    }


    public BigDecimal getValor() {
        return valor;
    }


    public String getDescricao() {
        return descricao;
    }


}
