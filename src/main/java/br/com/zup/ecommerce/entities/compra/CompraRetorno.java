package br.com.zup.ecommerce.entities.compra;

/**
 * Contagem de carga intrínseca da classe: 1
 */

public class CompraRetorno {

    private String status;
    //1
    private TipoPagamento tipoPagamento;
    private String nomeProduto;
    private int quantidade;
    private String comprador;

    public CompraRetorno(TipoPagamento tipoPagamento, String nomeProduto, int quantidade, String comprador) {
        this.status = "INICIADA";
        this.tipoPagamento = tipoPagamento;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.comprador = comprador;
    }

    public String getStatus() {
        return status;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getComprador() {
        return comprador;
    }
}
