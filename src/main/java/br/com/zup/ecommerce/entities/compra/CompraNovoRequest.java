package br.com.zup.ecommerce.entities.compra;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.validations.existeId.ExisteId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class CompraNovoRequest {

    @NotNull
    //2
    @ExisteId(dominioClasse = Produto.class, nomeCampo = "id")
    private Long idProduto;
    @NotNull
    @Positive
    private int quantidade;
    @NotNull
    //1
    private TipoPagamento tipoPagamento;

    public CompraNovoRequest(Long idProduto, int quantidade, TipoPagamento tipoPagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.tipoPagamento = tipoPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
}
