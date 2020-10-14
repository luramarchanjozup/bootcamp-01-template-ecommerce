package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CharacteristicsForm {
    
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String description;

    public CharacteristicsForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
