package br.com.zup.ecommerce.entities.produto;

import br.com.zup.ecommerce.entities.categoria.Categoria;
import br.com.zup.ecommerce.entities.produto.caracteristica.CaracteristicasProduto;
import br.com.zup.ecommerce.entities.produto.caracteristica.CaracteristicasProdutoNovoRequest;
import br.com.zup.ecommerce.entities.produto.imagem.ImagemProduto;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProduto;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProdutoNovoRequest;
import br.com.zup.ecommerce.entities.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contagem de carga intrínseca da classe: 8
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    //1
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    //1
    private List<OpiniaoProduto> opinioes = new ArrayList<>();

    private LocalDateTime dataCadastro = LocalDateTime.now();


    @Deprecated
    public Produto() {}

    //1
    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Min(0) int qtdDisponivel, @Size(min = 3) Set<CaracteristicasProdutoNovoRequest> caracteristicasProdutoNovoRequests, @NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        //1
        this.caracteristicasProduto = caracteristicasProdutoNovoRequests.stream()
                .map(cp -> cp.toModel(this))
                .collect(Collectors.toSet());
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Min(0) int qtdDisponivel, @NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
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

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<OpiniaoProduto> getOpinioes() {
        return opinioes;
    }

    public void incluirImagens(Set<String> imagens) {
        //1
        Set<ImagemProduto> imagensProduto = imagens.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagensProduto);
    }

    public void incluirOpinioes(OpiniaoProdutoNovoRequest opiniao) {
        this.opinioes.add(opiniao.toModel(this));
    }

    public boolean isDonoLogado(EntityManager manager, Usuario usuario) {
        return this.dono.equals(usuario);
    }
}
