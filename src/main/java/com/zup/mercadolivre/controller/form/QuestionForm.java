package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductQuestions;

public class QuestionForm {
    
    @NotNull @NotBlank
    private String title;

    public QuestionForm(){}

    public QuestionForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductQuestions toQuestion(User user, Product product) {
        return new ProductQuestions(this.title, user, product);
    }
}
