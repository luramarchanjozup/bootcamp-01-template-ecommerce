package com.zup.mercadolivre.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.services.validations.ExistsId;
import com.zup.mercadolivre.services.validations.UniqueValue;

/**
 * Handles the incoming {@link Category} creation information.
 * 
 * @author Matheus
 */
public class CategoryForm {
    @NotBlank
    @NotNull
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;
    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "name")
    private String parentCategory;

    public CategoryForm(String name, String parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    /**
     * Creates a new {@link Category} with a name and
     * <code>null parentCategory</code>.
     * 
     * <p>
     * If the form's parentCategory <code>isn't blank</code>, returns a new
     * {@link Category} with a parentCategory attached to it.
     * 
     * @throws NoResultException if parentCategory doesn't exist in the database
     * 
     * @param manager cannot be null
     * @return new {@link Category} with ParentCategory (if form's parentCategory
     *         isn't blank)
     */
    public Category toCategory(EntityManager manager) {
        Category category = new Category(this.name, null);

        if (!this.parentCategory.isBlank()) {
            category.setParentCategory(
                    manager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                            .setParameter("name", this.parentCategory).getSingleResult());

        }
        return category;
    }
}
