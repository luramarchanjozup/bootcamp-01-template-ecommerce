package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.encoder.SenhaEncoder;
import br.com.treino.ecommerce.model.Usuario;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    private @Email @NotBlank String login;
    @Size(min = 6 , message = "A senha deve ter no mínimo 6 caracteres")
    private @NotBlank String senha;

    public NovoUsuarioRequest(@Email @NotBlank String login,
                              @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
                       @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public Usuario toModel(){ //1
        return new Usuario(this.login, new SenhaEncoder(senha)); //2
    }

}
