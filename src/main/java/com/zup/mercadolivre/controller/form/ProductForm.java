package com.zup.mercadolivre.controller.form;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.Product;
import com.zup.mercadolivre.model.User;

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
    @NotNull @NotBlank
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

    public Product toProduct(Category category, User user) {
        return new Product(this.name, this.price, this.quantityInStock, this.description,
                category, user);
    }
}
