package com.github.marcoscoutozup.ecommerce.usuario;

import com.github.marcoscoutozup.ecommerce.validator.emailunico.EmailUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDTO {

    @NotBlank
    @Email
    @EmailUnico
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

            //1
    public Usuario toModel(){
        return new Usuario(email, senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
