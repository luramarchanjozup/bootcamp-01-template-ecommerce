package com.zup.mercadolivre.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.products.ProductCharacteristics;
import com.zup.mercadolivre.model.products.ProductOpinion;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantityInStock;
    private Float avarageNote;
    private Integer numberOfNotes;
    private Set<ProductCharacteristics> characteristics;
    private String description;
    private Category category;
    private LocalDateTime registrationTime;
    private UserDTO owner;
    private List<String> imagePaths;

    public ProductDTO(Long id, String name, Double price, Integer quantityInStock, Integer numberOfNotes, 
        Set<ProductCharacteristics> characteristics, String description, Category category, LocalDateTime registrationTime,
        UserDTO owner, List<String> imagePaths, Set<ProductOpinion> opinions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.avarageNote = getNoteAvarage(opinions);
        this.numberOfNotes = numberOfNotes;
        this.characteristics = characteristics;
        this.description = description;
        this.category = category;
        this.registrationTime = registrationTime;
        this.owner = owner;
        this.imagePaths = imagePaths;
    }

    private Float getNoteAvarage(Set<ProductOpinion> opinions) {
        Integer notesSum = 0;
        Integer noteSize = opinions.size();
        for (ProductOpinion opinion : opinions) {
            notesSum += opinion.getNote();
        }
        float result = notesSum.floatValue() / noteSize.floatValue();
        return Float.isNaN(result) ? 0f : result;
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

    public Set<ProductCharacteristics> getCharacteristics() {
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
