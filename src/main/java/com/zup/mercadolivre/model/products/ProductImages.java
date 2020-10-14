package com.zup.mercadolivre.model.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductImages {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    @ManyToOne
    private Product product;

    @Deprecated
    public ProductImages (){}

    public ProductImages(String imagePath, Product product) {
        this.imagePath = imagePath;
        this.product = product;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
