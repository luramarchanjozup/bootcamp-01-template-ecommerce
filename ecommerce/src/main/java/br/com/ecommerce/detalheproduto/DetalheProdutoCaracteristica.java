package br.com.ecommerce.detalheproduto;

import br.com.ecommerce.cadastroproduto.Caracteristica;

public class DetalheProdutoCaracteristica {

    private String nome;

    private String descricao;

    public DetalheProdutoCaracteristica(Caracteristica caracteristica) {

        this.nome = caracteristica.getNome();

        this.descricao = caracteristica.getValor();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
