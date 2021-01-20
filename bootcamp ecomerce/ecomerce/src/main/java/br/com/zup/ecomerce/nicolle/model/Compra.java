package br.com.zup.ecomerce.nicolle.model;

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

import br.com.zup.ecomerce.nicolle.enums.GatewayPagamento;
import br.com.zup.ecomerce.nicolle.request.RetornoPagseguroRequest;
import br.com.zup.ecomerce.nicolle.request.RetornoPaypalRequest;
import br.com.zup.ecomerce.nicolle.service.RetornoGatewayPagamento;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private @NotNull @Valid Produto produto;
	private @Positive int quantidade;
	
	@ManyToOne
	private @NotNull @Valid Usuario comprador;
	
	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	@Deprecated
	public Compra() {}

	public Compra(@NotNull @Valid Produto produtoParaCompra, @Positive int quantidade, 
			@NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.produto = produtoParaCompra;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}


	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", comprador=" + comprador
				+ ", gatewayPagamento=" + gatewayPagamento + ", transacoes=" + transacoes + "]";
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public String urlRedirecionamento(UriComponentsBuilder builder) {
		
		return this.gatewayPagamento.criaUrlRetorno(this, builder);
	}

	public void tentativaPagamento(@Valid RetornoGatewayPagamento request) {
		Transacao nova = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(nova), "já existe uma transação como essa processada!");
		
		Set<Transacao> transacoesComSucesso = transacoesConcluidasComSucesso();
		
		Assert.isTrue(transacoesComSucesso.isEmpty(), "Essa compra já foi concluida com sucesso!");
		
		this.transacoes.add(nova);
		
	}

	
	private Set<Transacao> transacoesConcluidasComSucesso(){
		Set<Transacao> transacoesComSucesso = this.transacoes.stream()
				.filter(Transacao :: concluidaComSucesso)
				.collect(Collectors.toSet());
		Assert.isTrue(transacoesConcluidasComSucesso().size() <= 1, "Há mais de uma mesma transação concluida com sucesso" + this.id);
		return transacoesConcluidasComSucesso();
	}
	
	public boolean processadaComSucesso() {
		
		return !transacoesConcluidasComSucesso().isEmpty();
	}
	
	

}
