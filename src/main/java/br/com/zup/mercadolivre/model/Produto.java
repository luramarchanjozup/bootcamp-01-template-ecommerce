package br.com.zup.mercadolivre.model;

import br.com.zup.mercadolivre.dto.request.CaracteristicaProdutoRequestDTO;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal valor;
    private int quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    private String descricao;

    @ManyToOne
    private Categoria categoria;

    private LocalDateTime instanteRegistro = LocalDateTime.now();

    @ManyToOne
    private Usuario dono;

    @ElementCollection
    private List<String> imagens;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @Positive int quantidade,
                   @Size(min = 3) @Valid Collection<CaracteristicaProdutoRequestDTO> caracteristicas,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
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

    public int getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProduto> getCaracteristica() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteRegistro() {
        return instanteRegistro;
    }

    public Usuario getDono() {
        return dono;
    }

    public <T> Set<T> mapCaracteristicas (Function<CaracteristicaProduto, T> mapFunction ) {
        return this.caracteristicas.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public boolean verificarSeEOProprietarioDoProduto(String login){
        return dono.getLogin().equals(login);
    }

    public void adicionarListaDeImagensNoProduto(List<String> imagens){
        this.imagens.addAll(imagens);
    }
}
