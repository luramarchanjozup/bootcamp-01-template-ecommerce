package com.zup.mercadolivre.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductCharacteristics;
import com.zup.mercadolivre.model.products.ProductOpinion;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantityInStock;
    private Float avarageNote;
    private Integer numberOfNotes;
    private List<ProductCharacteristics> characteristics;
    private String description;
    private Category category;
    private LocalDateTime registrationTime;
    private UserDTO owner;
    private List<String> imagePaths;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantityInStock = product.getQuantityInStock();
        this.avarageNote = getNoteAvarage(product);
        this.numberOfNotes = product.getOpinions().size();
        this.characteristics = product.getCharacteristics();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.registrationTime = product.getRegistrationTime();
        this.owner = product.getOwner().toDto();
        this.imagePaths = product.getImages().stream().map(x -> x.getImagePath()).collect(Collectors.toList());
    }

    private Float getNoteAvarage(Product product) {
        Integer notesSum = 0;
        Integer noteSize = product.getOpinions().size();
        for (ProductOpinion opinion : product.getOpinions()) {
            notesSum += opinion.getNote();
        }
        return notesSum.floatValue() / noteSize.floatValue();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public List<ProductCharacteristics> getCharacteristics() {
        return this.characteristics;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public LocalDateTime getRegistrationTime() {
        return this.registrationTime;
    }

    public UserDTO getOwner() {
        return this.owner;
    }

    public List<String> getImagePaths() {
        return this.imagePaths;
    }

    public Float getAvarageNote() {
        return this.avarageNote;
    }

    public Integer getNumberOfNotes() {
        return this.numberOfNotes;
    }

}
