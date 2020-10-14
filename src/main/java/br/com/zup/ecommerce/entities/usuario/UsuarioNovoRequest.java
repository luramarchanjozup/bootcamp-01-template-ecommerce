package br.com.zup.ecommerce.entities.usuario;

import br.com.zup.ecommerce.validations.valorUnico.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Contagem de carga intrínseca da classe: 3
 */

public class UsuarioNovoRequest {

    @NotBlank
    @Email
    //1
    @ValorUnico(dominioClasse = Usuario.class, nomeCampo = "login")
    private String login;
    @NotBlank
    @Size(min = 6, message = "deve ter tamanho igual ou maior que 6")
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    //1
    public Usuario toModel() {
        //1
        return new Usuario(this.login, new SenhaTratada(this.senha));
    }
}
