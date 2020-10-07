package br.com.ecommerce.cadastrousuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha =  senha;
    }

    public Usuario converterParaTipoUsuario(){
        return new Usuario(login, senha);
    }

}
