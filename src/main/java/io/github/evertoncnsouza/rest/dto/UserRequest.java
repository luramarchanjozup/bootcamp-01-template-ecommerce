package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.SenhaLimpa;
import io.github.evertoncnsouza.domain.entity.User;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UserRequest(@Email @NotBlank String email,
                       @NotBlank @Length(min = 6) String senha) {
        super();
        this.email = email;
        this.senha = senha;
    }
    public User toUser(){
        return new User(email, new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }
}
