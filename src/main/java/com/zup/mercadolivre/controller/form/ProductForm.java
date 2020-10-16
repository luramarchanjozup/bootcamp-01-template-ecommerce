package com.zup.mercadolivre.controller.form;

import java.util.ArrayList;
import java.util.List;

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
import com.zup.mercadolivre.model.products.ProductCharacteristics;
import com.zup.mercadolivre.services.UserService;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public List<CharacteristicsForm> getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(List<CharacteristicsForm> characteristics) {
        this.characteristics = characteristics;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
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
        Product product = new Product(this.name, this.price, this.quantityInStock, this.description, category, user);

        product.setCharacteristics(toProductCharacteristics(product));
        return product;

    }

    /**
     * @param product cannot be null
     * @return new list of {@link ProductCharacteristics} that is necessary for a
     *         new {@link Product}
     */
    private List<ProductCharacteristics> toProductCharacteristics(Product product) {
        List<ProductCharacteristics> characteristics = new ArrayList<>();

        for (CharacteristicsForm c : this.characteristics) {
            characteristics.add(new ProductCharacteristics(c.getName(), c.getDescription(), product));
        }
        return characteristics;
    }
}
