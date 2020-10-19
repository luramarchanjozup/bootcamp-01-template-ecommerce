package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.EntityManager;

public class CompraRequest {


    private Long quantidade;

    private GatewayPagamento gatewayPagamento;

    private Long produtoId;


    public CompraRequest(Long quantidade, GatewayPagamento gatewayPagamento, Long produtoId) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.produtoId = produtoId;
    }

    public Compra toModel(EntityManager entityManager, Usuario comprador){

        Produto produto = entityManager.find(Produto.class, produtoId);

        return new Compra(quantidade, gatewayPagamento, produto, comprador);
    }

    public Long getQuantidade() {
        return quantidade;
    }

}
