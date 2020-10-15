package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.SenhaLimpa;
import io.github.evertoncnsouza.domain.entity.Usuario;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//2 PCI's
public class UsuarioRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(@Email @NotBlank String email,
                              @NotBlank @Length(min = 6) String senha) {
        super();
        this.email = email;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        //como esse ponto do codigo sabe que tem que passar a senha limpa?
        return new Usuario(email,new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }

}
