package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.Compra;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.entity.Usuario;
import br.com.carlos.ecommerce.domain.entity.GatewayPagamento;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RequestCompraDto {

    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento pagamento;

    public Compra toEntity(EntityManager manager, Usuario comprador) {
        var produtoRecebido = manager.find(Produto.class, idProduto);
        System.out.println(produtoRecebido);
        return new Compra(quantidade, produtoRecebido, comprador, pagamento);

    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getPagamento() {
        return pagamento;
    }
}
