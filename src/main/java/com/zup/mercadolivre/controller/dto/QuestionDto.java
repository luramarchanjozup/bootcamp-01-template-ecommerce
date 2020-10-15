package com.zup.mercadolivre.controller.dto;

import com.zup.mercadolivre.model.products.ProductQuestions;

public class QuestionDto {
    
    private String title;
    private UserDTO owner;

    public QuestionDto(ProductQuestions questions) {
        this.title = questions.getTitle();
        this.owner = questions.getUser().toDto();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getOwner() {
        return this.owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

}
