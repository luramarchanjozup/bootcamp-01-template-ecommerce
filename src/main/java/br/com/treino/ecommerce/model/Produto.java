package br.com.treino.ecommerce.model;

import org.hibernate.validator.constraints.Length;

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

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @Positive BigDecimal valor;
    private @Min(1) Integer quantidade;
    @ManyToOne
    private @NotNull Categoria categoria; //1
    @Length(max = 1000)
    private @NotBlank String descricao;
    @ElementCollection
    @NotNull @Size(min = 3)
    private List<Caracteristica> caracteristicas = new ArrayList<>(); //2
    @ManyToOne
    private @NotNull @Valid Usuario dono; //3
    @ElementCollection
    private Set<ImagemProduto> imagemProduto = new HashSet<>(); //4
    private @NotNull LocalDateTime instanteCriacao;

    @Deprecated
    public Produto(){}

    public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Min(1) Integer quantidade,
                   @NotNull Categoria categoria, @Length(max = 1000) @NotBlank String descricao,
                   @NotNull @Size(min = 3) List<Caracteristica> caracteristicas,
                   @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.dono = dono;
        this.instanteCriacao = LocalDateTime.now();
    }

    public void associarImagem(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(link)) //5
                .collect(Collectors.toSet());
        this.imagemProduto.addAll(imagens);
    }

    public boolean produtoPertenceUsuario(String email) {
        return dono.getEmail().equals(email);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", categoria=" + categoria +
                ", descricao='" + descricao + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", dono=" + dono +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }



}
