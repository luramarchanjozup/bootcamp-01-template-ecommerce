package br.com.ecommerce.cadastrousuario;

import java.time.OffsetDateTime;

public class UsuarioRequest {

    private String login;

    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converterParaTipoUsuario(){
        return new Usuario(login, senha);
    }

}
