package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.api.handler.Unique;
import br.com.carlos.ecommerce.domain.entity.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RequestUsuarioDto {

    @NotBlank @Email @Unique(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank @Length(min = 6, message = "Tamanho mínimo de 6 carateres")
    private String senha;

    public Usuario toModel(){
        return  new Usuario(this.login, this.senha);
    }
}
