package com.zup.mercadolivre.controller.form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.services.UserService;
import com.zup.mercadolivre.services.validations.ExistsId;

/**
 * Handles the incoming {@link Product} creation information.
 * 
 * @author Matheus
 */
public class ProductForm {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotNull
    @Min(0)
    private Integer quantityInStock;
    @NotNull
    @Size(min = 3)
    private List<CharacteristicsForm> characteristics;
    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;
    @NotNull
    @NotBlank
    @ExistsId(domainClass = Category.class, fieldName = "name")
    private String category;

    public ProductForm(String name, Double price, Integer quantityInStock, List<CharacteristicsForm> characteristics,
            String description, String category) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.characteristics = characteristics;
        this.description = description;
        this.category = category;
    }

    /**
     * Creates a new {@link Product} with all the necessary information. The user in
     * the new Product is the logged one, received from {@link UserService}
     * 
     * @throws NoResultException if the informed {@link Category} is not found.
     * 
     * @param manager cannot be null
     * @return new {@link Product} with all the necessary information.
     */
    public Product toProduct(EntityManager manager) {
        Category category = manager.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                .setParameter("name", this.category).getSingleResult();
        User user = manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", UserService.authenticated().getUsername()).getSingleResult();
        Product product = new Product(this.name, this.price, this.quantityInStock, this.description, characteristics, category, user);

        return product;

    }

	public Set<String> getSameCharacteristics() {
        HashSet<String> sameNames = new HashSet<>();
        HashSet<String> results = new HashSet<>();

        for (CharacteristicsForm c : this.characteristics) {
            String name = c.getName();
            if (!sameNames.add(name)) {
                results.add(name);
            }
        }

		return results;
	}
}
