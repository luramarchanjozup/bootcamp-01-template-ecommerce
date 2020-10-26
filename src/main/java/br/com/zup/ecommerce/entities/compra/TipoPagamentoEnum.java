package br.com.zup.ecommerce.entities.compra;

public enum TipoPagamentoEnum {
    PAYPAL("http://paypal.com/%d?redirectUrl=%s"), PAGSEGURO("http://pagseguro.com?returnId=%d&redirectUrl=%s");

    private String link;

    TipoPagamentoEnum(String link) {
        this.link = link;
    }

    public String geLink() {
        return this.link;
    }
}
