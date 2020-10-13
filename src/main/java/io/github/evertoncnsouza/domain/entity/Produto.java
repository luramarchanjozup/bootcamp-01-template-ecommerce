package io.github.evertoncnsouza.domain.entity;

import ch.qos.logback.core.util.COWArrayList;
import io.github.evertoncnsouza.rest.dto.CaracteristicaRequest;
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
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//PCI 7
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

    @NotNull
    @Valid
    @ManyToOne
    private User dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    private final LocalDateTime horaCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE )
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @Positive int quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Valid Categoria categoria, @NotNull @Valid User dono,
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
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                ", horaCriacao=" + horaCriacao +
                ", imagens=" + imagens +
                '}';
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
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
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

    public User getDono() {
        return dono;
    }

    public Boolean pertenceAoUser(User possivelDono) {
        return this.dono.equals(possivelDono);

    }

    public <T> Set<T> mapeiaCaracteristicas(
            Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public void associaImagens(Set<String> links) {
        links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }
}


