package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Compra;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.shared.UsuarioLogado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    private @NotNull Long idProduto;
    private @Positive @NotNull int quantidade;
    private @NotBlank String meioPagamento;


    public NovaCompraRequest(@NotNull Long idProduto,
                             @Positive @NotNull int quantidade, @NotBlank String meioPagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.meioPagamento = meioPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    @Override
    public String toString() {
        return "NovaCompraRequest{" +
                "idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", meioPagamento='" + meioPagamento + '\'' +
                '}';
    }

    public Compra toCompra(Produto produto, UsuarioLogado comprador) {

        return new Compra(produto, this.quantidade,comprador.get(),
                this.meioPagamento);

    }
}
