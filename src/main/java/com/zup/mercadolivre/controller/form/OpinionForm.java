package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.model.products.Product;
import com.zup.mercadolivre.model.products.ProductOpinion;

public class OpinionForm {
    
    @NotNull @Min(1) @Max(5)
    private Integer note;
    @NotNull @NotBlank
    private String title;
    @NotNull @NotBlank @Size(max = 500)
    private String description;

    public OpinionForm(Integer note, String title, String description) {
        this.note = note;
        this.title = title;
        this.description = description;
    }

    public Integer getNote() {
        return this.note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public ProductOpinion toOpinion(Product product, User loggedUser) {
		return new ProductOpinion(this.note, this.title, this.description, loggedUser, product);
	}

}
