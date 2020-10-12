package br.com.ecommerce.cadastrousuario;

import br.com.ecommerce.validacao.Unico;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroUsuarioRequest {

    @NotBlank
    @Email
    @Unico(fieldName = "login", domainClass = Usuario.class)
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Deprecated
    public CadastroUsuarioRequest(){};

    public CadastroUsuarioRequest(@Valid @NotBlank @Email String login,
                                  @Valid @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converterParaTipoUsuario(){
        return new Usuario(login, senha);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
