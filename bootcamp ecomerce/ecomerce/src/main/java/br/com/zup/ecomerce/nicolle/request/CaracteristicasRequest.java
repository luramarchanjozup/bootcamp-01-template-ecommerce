package br.com.zup.ecomerce.nicolle.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.ecomerce.nicolle.model.CaracteristicaProduto;
import br.com.zup.ecomerce.nicolle.model.Produto;


public class CaracteristicasRequest {
	
		
	@NotBlank
	//@Size(max = 40)   //Limitar o quanto escrever, caso precise habilitar
	private String nome;
	
	@NotBlank
	//@Size(max = 400)
	private String descricao;

	public CaracteristicasRequest(@NotBlank String nome, @NotBlank String descricao) {
		
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicasRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public CaracteristicaProduto toCacteristica(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}



}
