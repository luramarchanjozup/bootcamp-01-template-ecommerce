package br.com.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.mercadolivre.model.Caracteristica;
import br.com.mercadolivre.model.Categoria;
import br.com.mercadolivre.model.Produto;
import br.com.mercadolivre.validator.IdExistente;
import br.com.mercadolivre.validator.ValorUnico;

//Contagem de Pontos - TOTAL:6
//1 - Produto
//1 - Caracteristica
//1 - Categoria
//1 - CaracteristicaDTO
//1 - For
//1 - If

public class ProdutoDTO {


	@NotBlank @ValorUnico(classe = Produto.class, campo = "nome")
	private String nome;
	@NotNull @Positive
	private int quantidade;
	@NotBlank @Length(max = 1000)
	private String descricao;
	@NotNull @Positive
	private BigDecimal valor;
	@NotNull @IdExistente(classe = "Categoria", campo = "id")
	private Long idCategoria;
	@Size(min = 3)
	private List<CaracteristicaDTO> caracteristicas = new ArrayList<>();
	
	
	public ProdutoDTO(@NotBlank String nome, @NotNull @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull @Positive BigDecimal valor,
			@NotNull Long idCategoria, @Size(min = 3) List<CaracteristicaDTO> caracteristicas) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas = caracteristicas;
	}
	
	
	public Produto toModel(EntityManager manager) {
		List<Caracteristica> caracList = new ArrayList<>();
		Categoria categoria = manager.find(Categoria.class, this.idCategoria);
		
		for(CaracteristicaDTO caracdto : this.caracteristicas) {
			Caracteristica caracteristica = caracdto.toModel();
			caracList.add(caracteristica);
		}
		
		return new Produto(this.nome, this.quantidade, this.descricao, this.valor, categoria, caracList);
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesUnicos = new HashSet<>();
		HashSet<String> nomesIguais = new HashSet<>();
		
		for (CaracteristicaDTO caracteristica : this.caracteristicas) {
			String nomeCaracteristica = caracteristica.getNome();
			if (!nomesUnicos.add(nomeCaracteristica)) {
				nomesIguais.add(nomeCaracteristica);
			}
		}
		return nomesIguais;
	}
	
	
	

	public List<CaracteristicaDTO> getCaracteristicas() {
		return caracteristicas;
	}


	public void setCaracteristicas(List<CaracteristicaDTO> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}


	@Override
	public String toString() {
		return "ProdutoDTO [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao + ", valor="
				+ valor + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}
	
	
	
}
