package br.com.carlos.ecommerce.domain.entity;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private int quantidade;

    @NotNull @Valid
    @ManyToOne
    private Produto produtoEscolhido;

    @NotNull @Valid
    @ManyToOne
    private Usuario comprador;

    @Enumerated @NotNull
    private GatewayPagamento gateway;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private final Set<Transacao> transacoes = new HashSet<>();


    @Deprecated
    public Compra(){}

    public Compra(@Positive int quantidade, @NotNull @Valid Produto produtoEscolhido, @NotNull @Valid Usuario comprador, @NotNull GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.produtoEscolhido = produtoEscolhido;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void setTransacao(Transacao novaTransacao) {
        //1
        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada " + novaTransacao);
        //1
        Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Transação duplicada, ja realizada com sucesso"+this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Produto getProdutoEscolhido() {
        return produtoEscolhido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getDonoProduto() {
        return produtoEscolhido.getDono();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Long getId() {
        return id;
    }

}
