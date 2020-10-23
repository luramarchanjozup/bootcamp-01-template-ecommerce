package com.zup.mercadolivre.model.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.controller.dto.OpinionDTO;
import com.zup.mercadolivre.model.User;

@Entity
public class ProductOpinion {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Min(1) @Max(5)
    private Integer note;
    @NotNull
    private String title;
    @NotNull @Size(max = 500)
    private String description;
    @NotNull @ManyToOne
    private User opnionOwner;
    @NotNull @ManyToOne
    private Product product;

    @Deprecated
    public ProductOpinion(){}

    public ProductOpinion(Integer note, String title, String description, User opnionOwner, Product product) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.opnionOwner = opnionOwner;
        this.product = product;
    }

    public Integer getNote() {
        return this.note;
    }

    public OpinionDTO toDto() {
        return new OpinionDTO(this.id, this.note, this.title, this.description, this.opnionOwner.toDto());
    }
}
