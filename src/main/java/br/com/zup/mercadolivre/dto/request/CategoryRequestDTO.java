package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.annotations.ExistsValue;
import br.com.zup.mercadolivre.annotations.UniqueValue;
import br.com.zup.mercadolivre.model.Category;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoryRequestDTO {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "already registered")
    private String name;

    @Positive
    @ExistsValue(domainClass = Category.class, fieldName = "id", message = "the id does not exist")
    private Long motherCategoryId;


    public CategoryRequestDTO(@NotBlank String name, @Positive Long motherCategoryId) {
        this.name = name;
        this.motherCategoryId = motherCategoryId;
    }

    public Category toModel(EntityManager entityManager) {
        Category category = new Category(name);
        if(motherCategoryId != null) {
            Category motherCategory = entityManager.find(Category.class, motherCategoryId);
            Assert.notNull(motherCategory, "O id da categoria mae precisa existir");
            category.setMotherCategory(motherCategory);
        }
        return category;
    }
}
