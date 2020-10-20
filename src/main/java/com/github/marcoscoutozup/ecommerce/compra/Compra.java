package com.github.marcoscoutozup.ecommerce.compra;

import com.github.marcoscoutozup.ecommerce.compra.enums.GatewayDePagamento;
import com.github.marcoscoutozup.ecommerce.compra.enums.StatusCompra;
import com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento.TentativaDePagamento;
import com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento.TentativaDePagamentoInterface;
import com.github.marcoscoutozup.ecommerce.produto.Produto;
import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING) //1
    private GatewayDePagamento gatewayDePagamento;

    @NotNull //2
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @NotNull
    @ManyToOne //3
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ManyToOne //4
    private Usuario comprador;

    @ElementCollection //5
    private List<TentativaDePagamento> transacoes;

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull GatewayDePagamento gatewayDePagamento, @NotNull StatusCompra statusCompra, @NotNull Produto produto, @NotNull @Positive Integer quantidade, @NotNull Usuario comprador) {
        this.gatewayDePagamento = gatewayDePagamento;
        this.statusCompra = statusCompra;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
    }

    public UUID getId() {
        return id;
    }

    public String retornarUrlDePagamentoDaCompra(String urlBase){
        return gatewayDePagamento.instanciaDoGatewayDePagamento.retornarUrlDePagamento(id, urlBase);
    }

    public String prepararDetalhesDaCompraParaEmailDoVendedor(){
        return "\n\n*** Olá, um usuário realizou o pedido do produto ***" +
                produto.prepararDetalhesDoProdutoParaEmail() +
                "Quantidade: " + quantidade +
                comprador.prepararDadosDoUsuarioParaEmail();
    }

    public String prepararDetalhesDeCompraParaEmailDoComprador(){
        return "\n\n*** Olá, aqui estão os detalhes do seu pedido ***" +
                produto.prepararDetalhesDoProdutoParaEmail() +
                "Quantidade: " + quantidade +
                "\n\nPreço Total: " + calcularValorTotalDaCompra() +
                "\n\n*** Quem comprou: ***" +
                comprador.prepararDadosDoUsuarioParaEmail();
    }

    public String prepararFalhaDeCompraParaEmailDoComprador(String urlBase){
        return "\n\n*** Oh não, seu pagamento não deu certo ***" +
                "\n\nAqui está a url para novo pagamento: " +
                retornarUrlDePagamentoDaCompra(urlBase) + "\n\n";
    }

    public GatewayDePagamento getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public void adicionarTentativaDePagamento(TentativaDePagamento tentativaDePagamento){
        Assert.isTrue(!verificarSeJaExisteTransacaoComSucessoParaACompra(), "Já existe uma transação com status SUCESSO para esta compra");
        transacoes.add(tentativaDePagamento);
    }

    public boolean verificarSeJaExisteTransacaoComSucessoParaACompra(){ //6
        return transacoes.stream().anyMatch(TentativaDePagamento::transacaoFoiUmSucesso);
    }

    public UUID retornarIdVendedor(){
        return this.produto.getUsuario().getId();
    }

    public UUID retornarIdComprador(){
        return this.comprador.getId();
    }

    public BigDecimal calcularValorTotalDaCompra(){
        return produto.getPreco()
                .multiply(new BigDecimal(quantidade))
                .setScale(2, RoundingMode.CEILING);
    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", gatewayDePagamento=" + gatewayDePagamento +
                ", status=" + statusCompra +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }
}
