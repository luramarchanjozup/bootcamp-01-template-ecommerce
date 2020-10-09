package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.cadastrocategoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.awt.image.ImageProducer;
import java.time.OffsetDateTime;

import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    @Positive
    private Long quantidadeDisponivel;

    @OneToMany(mappedBy = "produto")
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "produto")
    private List<ImagemProduto> imagens;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @ManyToOne
    private Categoria categoria;

    private OffsetDateTime instanteCadastro;

    public Produto(@NotBlank String nome, @NotNull @Positive Double valor, @NotNull @Positive Long quantidadeDisponivel,
                   List<Caracteristica> caracteristicas,
                   @NotBlank @Size(max = 1000) String descricao, Categoria categoria) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = OffsetDateTime.now();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Long quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProduto> imagens) {
        this.imagens = imagens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public OffsetDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(OffsetDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }
}
