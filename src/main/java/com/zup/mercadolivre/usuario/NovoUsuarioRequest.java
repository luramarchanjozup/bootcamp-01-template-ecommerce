package com.zup.mercadolivre.usuario;

import com.zup.mercadolivre.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Este email já existe")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public Usuario toModel() {
        return new Usuario(this.email, this.senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "NovoUsuarioRequest{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}