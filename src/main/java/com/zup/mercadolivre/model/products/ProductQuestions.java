package com.zup.mercadolivre.model.products;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.controller.dto.QuestionDto;
import com.zup.mercadolivre.model.User;

@Entity
public class ProductQuestions {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private LocalDateTime timeOfCreation;
    @NotNull @ManyToOne
    private User user;
    @NotNull @ManyToOne
    private Product product;

    @Deprecated
    public ProductQuestions(){}

    public ProductQuestions(String title, User user, Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
        this.timeOfCreation = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTimeOfCreation() {
        return this.timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public QuestionDto toDto() {
        return new QuestionDto(this);
    }
}
