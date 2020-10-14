package br.com.ecommerce.cadastrocategoria;

import br.com.ecommerce.validacao.Unico;
import javax.validation.constraints.NotBlank;

public class CadastroCategoriaRequest {

    @NotBlank
    @Unico(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    private Long categoriaMaeId;

    public CadastroCategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria converterParaTipoCategoria(){
        return new Categoria(nome,categoriaMaeId);
    }

}
