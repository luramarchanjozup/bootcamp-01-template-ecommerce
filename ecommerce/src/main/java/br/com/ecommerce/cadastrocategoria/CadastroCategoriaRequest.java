package br.com.ecommerce.cadastrocategoria;

import br.com.ecommerce.validacao.Unico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CadastroCategoriaRequest {

    @NotBlank
    @Unico(fieldName = "nome", domainClass = Categoria.class, message = "o nome da categoria precisa ser único")
    private String nome;

    private Long categoriaId;

    public CadastroCategoriaRequest(String nome, Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public Categoria converterParaTipoCategoria(EntityManager entityManager){


     return new Categoria(nome);

    }
}
