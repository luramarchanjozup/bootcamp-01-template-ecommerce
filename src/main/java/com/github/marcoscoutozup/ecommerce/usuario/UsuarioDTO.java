package com.github.marcoscoutozup.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDTO {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

            //1
    public Usuario toModel(){
        return new Usuario(login, senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
