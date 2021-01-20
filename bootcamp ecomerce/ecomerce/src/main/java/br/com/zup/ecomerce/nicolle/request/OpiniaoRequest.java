package br.com.zup.ecomerce.nicolle.request;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.ecomerce.nicolle.model.Opiniao;
import br.com.zup.ecomerce.nicolle.model.Produto;
import br.com.zup.ecomerce.nicolle.model.Usuario;

public class OpiniaoRequest {
	
	@Min(1)
	@Max(5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;

	public Opiniao novaOpiniao(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
		
		return new Opiniao(nota, titulo, descricao, produto, consumidor);
	}
	
//	@NotNull
//	private Long idUsuario;
//	
//	@NotNull
//	private Long idProduto;

}
