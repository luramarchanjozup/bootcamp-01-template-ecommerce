package com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProductCharacteristics {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull @ManyToOne
    private Product product;

    @Deprecated
    public ProductCharacteristics(){}

    public ProductCharacteristics(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
