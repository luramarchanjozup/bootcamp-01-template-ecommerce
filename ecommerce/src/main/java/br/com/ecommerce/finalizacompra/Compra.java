package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantidade;

    /* @complexidade = acoplamento contextual */
    @Enumerated
    private GatewayPagamento gatewayPagamento;

    /* @complexidade = acoplamento contextual */
    @NotNull
    @ManyToOne
    private Produto produto;

    /* @complexidade = acoplamento contextual */
    @NotNull
    @ManyToOne
    private Usuario usuario;

    /* @complexidade = acoplamento contextual */
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();


    @Deprecated
    public Compra() {}


    public Compra(Long quantidade, GatewayPagamento gatewayPagamento, Produto produtoEscolhido, Usuario comprador) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produtoEscolhido;
        this.usuario = comprador;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Compra [id=" + id + ", produtoEscolhido=" + produto
                + ", quantidade=" + quantidade + ", gatewayPagamento=" + gatewayPagamento + ", transacoes="
                + transacoes + "]";
    }


    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {

        return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);

    }


    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {

        var novaTransacao = request.toTransacao(this);
        boolean naoExisteTransacaoIgual = !this.transacoes.contains(novaTransacao);

        Assert.state(naoExisteTransacaoIgual, "Já existe uma transacao igual a essa processada" + novaTransacao);
        Assert.state(transacoesConcluidasComSucesso().isEmpty(), "Esse compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);

    }


    private Set<Transacao> transacoesConcluidasComSucesso() {

        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes
                .stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(
                transacoesConcluidasComSucesso.size() <= 1,
                "Algum erro aconteceu. Mais de uma transacao concluida com sucesso aqui na compra " + this.id
        );

        return transacoesConcluidasComSucesso;

    }


    public boolean processadaComSucesso() {

        return !transacoesConcluidasComSucesso().isEmpty();

    }

}
