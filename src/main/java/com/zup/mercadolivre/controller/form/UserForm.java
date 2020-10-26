package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.CleanPassword;
import com.zup.mercadolivre.model.User;

public class UserForm {

    @NotNull @NotBlank @Email
    private String email;
    @NotNull @NotBlank @Size(min = 6)
    private String password;

    public UserForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public User toUser() {
        return new User(this.email, new CleanPassword(this.password));
    }
}
