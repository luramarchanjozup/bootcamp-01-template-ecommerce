package br.com.mercadolivre.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

//Contagem de Pontos - TOTAL:7
//1 - Categoria
//1 - Caracteristica
//1 - Imagem
//1 - Usuario
//1 - Pergunta
//1 - Opiniao
//1 - If

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Positive
	private int quantidade;
	@NotBlank @Length(max = 1000)
	private String descricao;
	@NotNull @Positive
	private BigDecimal valor;
	@NotNull @ManyToOne
	private Categoria categoria;
	@OneToMany(cascade = {CascadeType.ALL})
	private List <Caracteristica> caracteristicas;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set <Imagem> imagens;
	@ManyToOne
	Usuario usuario;
	@OneToMany(mappedBy = "produto") @OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	
	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @Positive int quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull Categoria categoria, List<Caracteristica> caracteristicas) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.caracteristicas = caracteristicas;
	}

	
	public void adicionarImagens(Set<String> linksImagens) {
		Set<Imagem> imagens = linksImagens.stream()
				.map(link -> new Imagem(link, this))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);
	}
	

	public <T> Set<T> mapeiaCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaImagens(Function<Imagem, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora)
				.collect(Collectors.toCollection(TreeSet :: new));
	}
	
	public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}

	public boolean abataEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque "+quantidade);
		if(quantidade <= this.quantidade) {
			this.quantidade-=quantidade;
			return true;		
		}	
		return false;
	}
	

	public String getNome() {
		return nome;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public String getDescricao() {
		return descricao;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}


	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao + ", valor=" + valor
				+ ", categoria=" + categoria + ", caracteristicas=" + caracteristicas + "]";
	}

	
	
	
}
