package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.api.handler.Unique;
import br.com.carlos.ecommerce.domain.entity.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RequestCategoriaDto {

    @NotBlank @Unique(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    private Long idCategoria;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria toEntity(EntityManager manager){
        var categoria = new Categoria(nome);
        if(idCategoria != null){
            Categoria categoriaMae = manager.find(Categoria.class, idCategoria);
            Assert.notNull(categoriaMae, "Categoria mãe não encontrada");
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

}
