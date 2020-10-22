package br.com.carlos.ecommerce.domain.entity;

import br.com.carlos.ecommerce.api.dto.RequestCaracteristicaDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank @Length(max = 1000)
    private String descricao;

    @Positive
    private int quantidade;

    @Positive
    private BigDecimal valor;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @Valid @NotNull @ManyToOne
    private Usuario comprador;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @CreationTimestamp
    private Date timestamp;


    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotBlank @Length(max = 1000) String descricao, @Positive int quantidade,
                   @Positive BigDecimal valor, Categoria categoria, Collection<RequestCaracteristicaDto> caracteristicas,
                   @Valid @NotNull Usuario comprador) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3,
                "Todo produto precisa ter no mínimo 3 ou mais características");
        this.comprador = comprador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (nome == null) {
            return other.nome == null;
        } else return nome.equals(other.nome);
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.comprador.equals(possivelDono);
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
