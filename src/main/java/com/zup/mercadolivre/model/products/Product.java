package com.zup.mercadolivre.model.products;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.Category;
import com.zup.mercadolivre.model.User;

import org.springframework.security.authentication.BadCredentialsException;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotNull
    @Min(0)
    private Integer quantityInStock;
    @NotNull
    @Size(min = 3) @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProductCharacteristics> characteristics;
    @NotNull
    @Size(max = 1000)
    private String description;
    @NotNull @ManyToOne
    private Category category;
    @NotNull
    private LocalDateTime registrationTime;
    @NotNull
    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProductImages> images;

    @Deprecated
    public Product() {
    }

    public Product(String name, Double price, Integer quantityInStock, 
            String description, Category category, User owner) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.category = category;
        this.registrationTime = LocalDateTime.now();
        this.owner = owner;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProductCharacteristics> getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getRegistrationTime() {
        return this.registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<ProductImages> getImages() {
        return this.images;
    }

    public void setImages(ProductImages image) {
        this.images.add(image);
    }

    public void checkOwnership(String email) {
        if (!email.equals(this.owner.getEmail())) {
            throw new BadCredentialsException("This user does not own the product");
        }
    }
}
