package br.com.ecommerce.finalizacompra;

public enum StatusRetornoPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {

        return this.equals(SUCESSO) ? StatusTransacao.sucesso : StatusTransacao.erro;

    }

}
