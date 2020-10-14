package br.com.zup.ecommerce.entities.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Contagem de carga intrínseca da classe: 0
 */

public class SenhaTratada {

    private String senha;

    public SenhaTratada(@NotBlank @Size(min = 6) String senha) {

        Assert.notNull(senha, "A senha não pode ser vazia");
        Assert.isTrue(senha.length() >= 6, "A senha deve ter tamanho igual ou maior que 6");

        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public String getSenha() {
        return senha;
    }

}
