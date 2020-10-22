package br.com.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mercadolivre.dto.RetornoGatewayGenericoPagamento;
import br.com.mercadolivre.dto.enums.GatewayPagamentoEnum;

//Contagem de Pontos - TOTAL:6
//1 - RetornoGatewayGenericoPagamento
//1 - GatewayPagamentoEnum
//1 - Usuario
//1 - Produto
//1 - Transacao
//1 - this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet()

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive
	private int quantidade;
	@ManyToOne @NotNull @Valid
	private Produto produtoEscolhido;
	@ManyToOne @NotNull @Valid
	private Usuario comprador;
	@Enumerated @NotNull
	private GatewayPagamentoEnum gatewayPagamento;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	
	@Deprecated
	public Compra() {	
	}
	
	public Compra(@Positive int quantidade, @NotNull @Valid Produto produtoEscolhido, @NotNull @Valid Usuario comprador,
			@NotNull GatewayPagamentoEnum gatewayPagamento) {
		super();
		this.quantidade = quantidade;
		this.produtoEscolhido = produtoEscolhido;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}


	public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
	}
	
	public void adicionaTransacao(@Valid RetornoGatewayGenericoPagamento retorngatewayogenerico) {
		Transacao novaTransacao = retorngatewayogenerico.toTransacao(this);

		Assert.state(!this.transacoes.contains(novaTransacao), "Já existe uma transacao igual a essa processada "+ novaTransacao);
		Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");

		this.transacoes.add(novaTransacao);
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Tem mais de uma transacao concluida com sucesso aqui na compra "+this.id);
		
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}
	
	public int getQuantidade() {
		return quantidade;
	}


	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}


	public Usuario getComprador() {
		return comprador;
	}


	public GatewayPagamentoEnum getGatewayPagamento() {
		return gatewayPagamento;
	}
	
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Compra [quantidade=" + quantidade + ", produtoEscolhido=" + produtoEscolhido + ", comprador="
				+ comprador + ", gatewayPagamento=" + gatewayPagamento + "]";
	}
	
	
	
}
