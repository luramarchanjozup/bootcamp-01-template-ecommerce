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

    public QuestionDto toDto() {
        return new QuestionDto(this.title, this.user.toDto());
    }
}
