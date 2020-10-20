package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.util.SenhaEncoder;
import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.shared.validations.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @ValorUnico(nomeClasse = Usuario.class, nomeCampo = "email",
            message = "Usuário já cadastrado")
    private @Email @NotBlank String email;
    @Size(min = 6 , message = "A senha deve ter no mínimo 6 caracteres")
    private @NotBlank String senha;

    public NovoUsuarioRequest(@Email @NotBlank String email,
                              @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
                       @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public Usuario toModel(){ //1
        return new Usuario(this.email, new SenhaEncoder(senha)); //2
    }

}
