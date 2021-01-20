package br.com.zup.ecomerce.nicolle.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.zup.ecomerce.nicolle.request.CaracteristicasRequest;
@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private @NotBlank String nome;
	private @NotNull @Min(1) BigDecimal valor;
	private @NotNull @PositiveOrZero int quantidade;
	private @NotBlank @Size(max = 1000) String descricao;
	private @NotNull @Valid @ManyToOne Categoria categoria;
	private @NotNull @Valid @ManyToOne Usuario vendedor;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	private LocalDate createdAt = LocalDate.now();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private Set<Pergunta> perguntas =  new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	
	@Deprecated
	public Produto() {}

	public Produto(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @PositiveOrZero int quantidade,
			@NotBlank @Size(max = 1000) String descricao, Collection<CaracteristicasRequest> caracteristicas,
			@NotNull @Valid Categoria categoria, @NotNull Usuario vendedor) {
		
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao =descricao;
		this.categoria = categoria;
		this.vendedor = vendedor;
		this.caracteristicas.addAll( caracteristicas.stream().map(caracteristica -> caracteristica.toCacteristica(this))
				.collect(Collectors.toSet()));
		
		Assert.isTrue(this.caracteristicas.size() >=3, "Todo produto deve conter pelo menos 3 características!");
	}

	public String getNome() {
		return this.nome;
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return this.descricao;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(Set<CaracteristicaProduto> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
					.collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelVendedor) {
		
		return this.vendedor.equals(possivelVendedor);
	}

	public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
		
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public boolean tiraEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para comprar e retirar do estoque" + quantidade);
		
		if(quantidade <= this.quantidade) {
			this.quantidade-=quantidade;
			return true;
		}
		 return false;
		
	}
	
}
