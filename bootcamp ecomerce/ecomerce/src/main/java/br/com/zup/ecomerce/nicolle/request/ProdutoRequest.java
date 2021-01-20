package br.com.zup.ecomerce.nicolle.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.ecomerce.nicolle.model.Categoria;
import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;
import br.com.zup.ecomerce.nicolle.repository.CategoriasRepository;

public class ProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Min(1) // apesar de ter achado norma do mercado livre com preço min de 7 reais, achei anuncio de 1 real minimo de valor...então setei como 1
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private int quantidade;
	
	@Size(min = 3)
	private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();
	
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	private Long categoriaId;
	
//	@NotBlank
//	private Long usuarioId;
	
	
	
	
	public String getNome() {
		return nome;
	}
	public ProdutoRequest(@NotBlank String nome, @NotBlank @Min(1) BigDecimal valor,
			@NotBlank @Min(0) @PositiveOrZero int quantidade,
			@Size(min = 3) List<CaracteristicasRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			Long categoriaId) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas.addAll(caracteristicas);
		this.descricao = descricao;
		this.categoriaId = categoriaId;
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
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public List<CaracteristicasRequest> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<CaracteristicasRequest> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	
	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", caracteristicas="
				+ caracteristicas + ", descricao=" + descricao + ", categoriaId=" + categoriaId + "]";
	}
	
	public Produto novoProduto(CategoriasRepository categoriaRepository, Usuario vendedor) {
		
		Categoria categoria = categoriaRepository.findById(categoriaId).get();
		
		return new Produto(nome, valor, quantidade, descricao, caracteristicas, categoria, vendedor);
	}
	
	
	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		
		for (CaracteristicasRequest caracteristica : caracteristicas) {
			
			String nomeCaracteristica = caracteristica.getNome();
			
			if(!nomesIguais.add(caracteristica.getNome())) {
				resultados.add(nomeCaracteristica);
			}
		}
		
		return resultados;
	}

}
