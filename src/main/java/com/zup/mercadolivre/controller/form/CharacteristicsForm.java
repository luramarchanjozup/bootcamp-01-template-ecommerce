package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductCharacteristics;

public class CharacteristicsForm {
    
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String description;

    public CharacteristicsForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductCharacteristics toCharacteristic(Product product) {
        return new ProductCharacteristics(this.name, this.description, product);
    }
}
