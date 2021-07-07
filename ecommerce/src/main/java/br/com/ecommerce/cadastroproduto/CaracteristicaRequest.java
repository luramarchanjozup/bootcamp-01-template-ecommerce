package br.com.ecommerce.cadastroproduto;


public class CaracteristicaRequest {

    private String nome;

    private String valor;


    public Caracteristica toModel(Produto produto){

        return new Caracteristica(nome, valor, produto);

    }

    public CaracteristicaRequest(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
