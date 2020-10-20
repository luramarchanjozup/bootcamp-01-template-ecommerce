package io.github.evertoncnsouza.domain.entity;

import io.github.evertoncnsouza.domain.repository.RetornoGatewayPagamento;
import io.github.evertoncnsouza.domain.enums.GatewayPagamento;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// 9 PCI's?
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoEscolhido;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario navegador;

    @NotNull
    private GatewayPagamento gateway;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produtoEscolhido,
                  @Positive int quantidade, @NotNull @Valid Usuario navegador,
                  @NotNull GatewayPagamento gateway) {
        this.produtoEscolhido = produtoEscolhido;
        this.quantidade = quantidade;
        this.navegador = navegador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public Usuario getNavegador() {
        return navegador;
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);

        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada" + novaTransacao);

        Assert.state(transacoesConcluidasComSucesso().isEmpty(), "Esta compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Mais de uma transação concluída com sucesso" + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getDonoProduto() {
        return produtoEscolhido.getDono();
    }

    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quantidade=" + quantidade +
                ", navegador=" + navegador +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }
}

