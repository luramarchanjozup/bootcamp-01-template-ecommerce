package br.com.ecommerce.finalizacompra;

import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.Usuario;

import javax.persistence.EntityManager;

public class CompraRequest {

    private Long quantidade;

    private Long produtoId;


    public CompraRequest(Long quantidade, Long produtoId, Long usuarioId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

}
