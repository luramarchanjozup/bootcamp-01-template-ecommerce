package com.github.marcoscoutozup.ecommerce.compra;

import com.github.marcoscoutozup.ecommerce.compra.enums.GatewayDePagamento;
import com.github.marcoscoutozup.ecommerce.compra.enums.StatusCompra;
import com.github.marcoscoutozup.ecommerce.produto.Produto;
import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import com.github.marcoscoutozup.ecommerce.validator.enumvalido.GatewayPagamento;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDTO {

    @NotNull
    @GatewayPagamento
    private String gatewayDePagamento;

    @NotNull
    @Positive
    private Integer quantidade;
                                                                                //1
    public Compra toModel(EntityManager entityManager, String emailDoComprador, Produto produto){
        //2
        Usuario comprador = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", emailDoComprador)
                .getSingleResult();

        //3
        GatewayDePagamento gatewayDePagamento = GatewayDePagamento.converterStringParaGatewayDePagamento(this.gatewayDePagamento);

        return new Compra(gatewayDePagamento, StatusCompra.INICIADA, produto, quantidade, comprador);
    }

    public String getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public void setGatewayDePagamento(String gatewayDePagamento) {
        this.gatewayDePagamento = gatewayDePagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
