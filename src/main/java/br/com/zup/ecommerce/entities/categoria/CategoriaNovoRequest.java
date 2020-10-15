package br.com.zup.ecommerce.entities.categoria;

import br.com.zup.ecommerce.validations.valorUnico.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class CategoriaNovoRequest {

    @NotBlank
    //1
    @ValorUnico(dominioClasse = Categoria.class, nomeCampo = "nome")
    private String nome;

    private Long categoriaMaeId;

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    //1
    public Categoria toModel(EntityManager manager) {

        Categoria categoria = null;

        //1
        if(this.categoriaMaeId != null) {
            categoria = manager.find(Categoria.class, this.categoriaMaeId);
        }

        return new Categoria(this.nome, categoria);
    }
}
