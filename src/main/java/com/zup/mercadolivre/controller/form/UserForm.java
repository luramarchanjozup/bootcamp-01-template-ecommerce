package com.zup.mercadolivre.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.mercadolivre.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User toUser(BCryptPasswordEncoder encoder) {
        return new User(this.email, encoder.encode(this.password));
    }
}
