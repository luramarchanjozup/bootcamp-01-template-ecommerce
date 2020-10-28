package br.com.zup.ecommerce.entities.categoria;

/**
 * Contagem de carga intrínseca da classe: 2
 */

public class CategoriaRetorno {

    private String nome;
    private CategoriaRetorno categoriaMae;

    //1
    public CategoriaRetorno(Categoria categoria) {
        this.nome = categoria.getNome();
        //1
        if (categoria.getCategoriaMae() != null) {
            this.categoriaMae = new CategoriaRetorno(categoria.getCategoriaMae());
        }
    }

    public String getNome() {
        return nome;
    }

    public CategoriaRetorno getCategoriaMae() {
        return categoriaMae;
    }
}
