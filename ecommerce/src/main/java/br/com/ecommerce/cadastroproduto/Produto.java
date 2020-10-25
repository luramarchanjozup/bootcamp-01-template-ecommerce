package br.com.ecommerce.cadastroproduto;

import br.com.ecommerce.adicionaropiniao.Opiniao;
import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastrousuario.Usuario;
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
import java.util.Set;
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

    //1
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas = new ArrayList<>();
    //1
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opiniao> opinioes = new ArrayList<>();
    //1
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens = new ArrayList<>();
    //1
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Pergunta> perguntas = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    //1
    @NotNull
    @ManyToOne
    private Categoria categoria;

    //1
    @ManyToOne
    private Usuario usuario;

    private OffsetDateTime instanteCadastro;


    @Deprecated
    public Produto(){};

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Long quantidadeDisponivel,
                   List<CaracteristicaRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
                   Categoria categoria, Usuario usuario) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;

        //1
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toList()));

        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.instanteCadastro = OffsetDateTime.now();

    }

    //1
    public List<String> listarLinks(Function<ImagemProduto, String> funcaoDeListagem){

        return this.imagens
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());

    }

    //1
    public <T> List<T> listarCaracteristicas(Function<Caracteristica, T> funcaoDeListagem){

        return this.caracteristicas
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());

    }

    //1
    public List<String> listarOpinioes(Function<Opiniao, String> funcaoDeListagem){

        return this.opinioes
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }

    //1
    public List<String> listarPerguntas(Function<Pergunta, String> funcaoDeListagem){

        return this.perguntas
                .stream()
                .map(funcaoDeListagem)
                .collect(Collectors.toList());
    }

    //1
    public void associaImagens(List<String> links) {

        List<ImagemProduto> imagens = links
                .stream()
                .map(link -> new ImagemProduto(link, this))
                .collect(Collectors.toList());

        this.imagens.addAll(imagens);

    }

    //1
    public boolean verificaDisponibilidadeEAtualiza(Long quantidadeDemandada){

        boolean resposta = quantidadeDisponivel >= quantidadeDemandada;

        this.quantidadeDisponivel = quantidadeDisponivel - quantidadeDemandada;

        return resposta;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public void setOpinioes(List<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProduto> imagens) {
        this.imagens = imagens;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
