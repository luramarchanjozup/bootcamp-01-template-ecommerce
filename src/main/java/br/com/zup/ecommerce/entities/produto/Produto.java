package br.com.zup.ecommerce.entities.produto;

import br.com.zup.ecommerce.entities.categoria.Categoria;
import br.com.zup.ecommerce.entities.produto.caracteristica.CaracteristicasProduto;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 3
 */

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private int qtdDisponivel;

    @Size(min = 3)
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produto")
    //1
    private Set<CaracteristicasProduto> caracteristicasProduto = new HashSet<>();

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne
    //1
    private Categoria categoria;

    @NotNull
    @Valid
    @ManyToOne
    //1
    private Usuario dono;

    private LocalDateTime dataCadastro = LocalDateTime.now();


    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Min(0) int qtdDisponivel, @Size(min = 3) Set<CaracteristicasProduto> caracteristicasProduto, @NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.caracteristicasProduto = caracteristicasProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public Set<CaracteristicasProduto> getCaracteristicasProduto() {
        return caracteristicasProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Usuario getDono() {
        return dono;
    }
}
