package br.com.zup.ecommerce.entities.produto.caracteristica;

/**
 * Contagem de carga intrínseca da classe: 1
 */

public class CaracteristicasProdutoRetorno {

    private String nome;
    private String descricao;

    //1
    public CaracteristicasProdutoRetorno(CaracteristicasProduto caracteristicas) {
        this.nome = caracteristicas.getNome();
        this.descricao = caracteristicas.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
