package br.com.zup.ecommerce.entities.compra;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.usuario.Usuario;
import br.com.zup.ecommerce.validations.existeId.ExisteId;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Contagem de carga intrínseca da classe: 5
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
    private TipoPagamentoEnum tipoPagamento;

    public CompraNovoRequest(Long idProduto, int quantidade, TipoPagamentoEnum tipoPagamento) {
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

    public TipoPagamentoEnum getTipoPagamento() {
        return tipoPagamento;
    }
    
    //2
    public Compra toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario comprador){
        
        Assert.notNull(produto, "Produto não encontrado");
        Assert.notNull(comprador, "Usuário comprador não encontrado");
        
        return new Compra(produto, this.quantidade, this.tipoPagamento, comprador);
    }
}
