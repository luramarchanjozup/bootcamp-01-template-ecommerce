package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Categoria;
import io.github.evertoncnsouza.validation.constraintvalidation.ExistsId;
import io.github.evertoncnsouza.validation.constraintvalidation.UniqueValue;
import org.springframework.util.Assert;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

//Não tem PCI
public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest [nome=" + nome + ", idCategoriaMae="
                + idCategoriaMae + "]";
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria mae precisa ser válido");

            categoria.setMae(categoriaMae);
        }
        return categoria;
    }
}

