package com.github.marcoscoutozup.ecommerce.compra;

import com.github.marcoscoutozup.ecommerce.compra.enums.GatewayDePagamento;
import com.github.marcoscoutozup.ecommerce.compra.enums.Status;
import com.github.marcoscoutozup.ecommerce.produto.Produto;
import com.github.marcoscoutozup.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private Status status;

    @NotNull
    @ManyToOne //3
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ManyToOne //4
    private Usuario comprador;

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull GatewayDePagamento gatewayDePagamento, @NotNull Status status, @NotNull Produto produto, @NotNull @Positive Integer quantidade, @NotNull Usuario comprador) {
        this.gatewayDePagamento = gatewayDePagamento;
        this.status = status;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
    }

    public String retornarUrlDePagamentoDaCompra(){
        return gatewayDePagamento.instanciaDoGatewayDePagamento.retornarUrlDePagamento(id);
    }

    public String prepararDetalhesDaCompraParaEmail(){
        return "\n\n*** Olá, um usuário realizou o pedido do produto ***" +
                "\n\nProduto: " + produto.getNome() +
                "\n\nUsuário: " + comprador.getEmail() +
                "\n\n";
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", gatewayDePagamento=" + gatewayDePagamento +
                ", status=" + status +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }
}
