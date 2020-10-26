package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.annotations.ExistsValue;
import br.com.zup.mercadolivre.annotations.UniqueValue;
import br.com.zup.mercadolivre.model.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequestDTO {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "already registered")
    private String nome;

    @Positive
    @ExistsValue(domainClass = Categoria.class, fieldName = "id", message = "the id does not exist")
    private Long categoriaMaeId;


    public CategoriaRequestDTO(@NotBlank String nome, @Positive Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager entityManager) {
        Categoria categoria = new Categoria(nome);
        if(categoriaMaeId != null) {
            Categoria categoriaMae = entityManager.find(Categoria.class, categoriaMaeId);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
