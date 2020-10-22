package io.github.evertoncnsouza.domain.entity;

import io.github.evertoncnsouza.rest.dto.CaracteristicaRequest;
import io.github.evertoncnsouza.rest.dto.SitePerguntaResponse;
import io.github.evertoncnsouza.rest.dto.SiteOpiniaoResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//10 PCI's- Contabilizei 10,  com os atributos Categoria e dono.
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private int quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;
    //PCI 1;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;
    //PCI 2


    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    //PCI 3

    private final LocalDateTime horaCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    //PCI 4

   @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();
   //PCI 5

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Pergunta> perguntas = new HashSet<>();
    //PCI 6

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @Positive int quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono,
                   @Size(min = 3) @Valid Collection<CaracteristicaRequest> caracteristicas) {

        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3,
                "Todo produto ter no minimo 3 caracteristicas");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return getNome().equals(produto.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean pertenceAoUser(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }


    public <T> Set<T> mapeiaCaracteristicas(
            Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }
    //PCI 7

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaMapeadora) {
        return this.imagens.stream().map(funcaMapeadora)
                .collect(Collectors.toSet());
    }
    //PCI 8

    public void associaImagens(Set<String> links) {
        links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }
    //PCI 9

    public SiteOpiniaoResponse getOpinioes() {
        return new SiteOpiniaoResponse(this.opinioes);
    }

    public SitePerguntaResponse getPerguntas() {
        return new SitePerguntaResponse(this.perguntas);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +

                '}';
    }

    //PCI 10
        public boolean abataEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade>0, "Quantidade necessita ser maior que zero");
        if(quantidade<= this.quantidade) {
            this.quantidade-=quantidade;
            return true;
        }
        return false;
    }
}


