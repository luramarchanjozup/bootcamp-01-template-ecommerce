package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.annotations.UniqueValue;
import br.com.zup.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequestDTO {

    @NotBlank(message = "o login deve ser preenchido") @Email(message = "endereço de email invalido")
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "already registered")
    private String login;

    @NotBlank (message = "a senha deve ser preenchida") @Length(min = 6, message = "tamanho mínimo 6")
    private String senha;


    @Deprecated
    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(@NotBlank(message = "o nome deve ser preenchido") @Email(message = "endereço de email invalido") String login, @NotBlank(message = "a senha deve ser preenchida") @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
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

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }
}
