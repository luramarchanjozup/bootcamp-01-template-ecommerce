package io.github.evertoncnsouza.domain.entity;

import io.github.evertoncnsouza.domain.enums.GatewayPagamento;
import io.github.evertoncnsouza.domain.enums.StatusCompra;
import io.github.evertoncnsouza.domain.repository.RetornoGatewayPagamento;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// 5 PCI's?
@Entity
public class  Compra {

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
    private GatewayPagamento gateway ;


    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();


    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;


    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produtoEscolhido, @Positive int quantidade,
                  @NotNull @Valid Usuario navegador, @NotNull GatewayPagamento gateway,
                   @NotNull StatusCompra statusCompra) {
        this.produtoEscolhido = produtoEscolhido;
        this.quantidade = quantidade;
        this.navegador = navegador;
        this.gateway = gateway;
        this.statusCompra = statusCompra;
    }
    
    public Long getId() {
        return id;
    }

    public Usuario getNavegador() {
        return navegador;
    }


    public Usuario getDonoProduto() {
        return produtoEscolhido.getDono();
    }

    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }
    //PCI 1


    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        //PCI 2
        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);
        //PCI 3
        Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }
    //PCI 3 e 4
    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "Existe mais de uma transacao concluida com sucesso aqui nesta compra "+this.id);
        return transacoesConcluidasComSucesso;
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
                ", statusCompra=" + statusCompra +
                '}';
    }

    //PCI 5
        public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}

