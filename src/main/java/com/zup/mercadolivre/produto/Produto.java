package com.zup.mercadolivre.produto;

import com.zup.mercadolivre.categoria.Categoria;
import com.zup.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import com.zup.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import com.zup.mercadolivre.produto.imagem.ImagemProduto;
import com.zup.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotNull @Positive BigDecimal valor;
    private @Positive int quantidade;
    private @NotBlank @Length(max = 1000) String descricao;
    private @ManyToOne @NotNull @Valid Categoria categoria;
    private @ManyToOne Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono,
                   @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
        Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 características");
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }

    public Usuario getDono() {
        return this.dono;
    }
}
