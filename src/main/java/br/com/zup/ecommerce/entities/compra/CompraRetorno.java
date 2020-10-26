package br.com.zup.ecommerce.entities.compra;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class CompraRetorno {

    //1
    private StatusPagamentoEnum status;
    //1
    private TipoPagamentoEnum tipoPagamento;
    private String nomeProduto;
    private int quantidade;
    private String comprador;
    
    //1
    public CompraRetorno(Compra compra) {
        this.status = compra.getStatus();
        this.tipoPagamento = compra.getTipoPagamento();
        this.nomeProduto = compra.getProduto().getNome();
        this.quantidade = compra.getQuantidade();
        this.comprador = compra.getComprador().getLogin();
    }
    
    public StatusPagamentoEnum getStatus() {
        return status;
    }

    public TipoPagamentoEnum getTipoPagamento() {
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

    @Override
    public String toString() {
        return "Dados do produto:" +
                "\nStatus da compra: " + status +
                "\nForma de pagamento: " + tipoPagamento +
                "\nProduto: " + nomeProduto +
                "\nQuantidade: " + quantidade +
                "\nComprador: " + comprador;
    }
}
