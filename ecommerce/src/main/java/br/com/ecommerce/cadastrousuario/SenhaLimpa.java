package br.com.ecommerce.cadastrousuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    @NotBlank
    private String textoLimpo;

    public SenhaLimpa(@NotBlank @Valid String textoLimpo) {
        this.textoLimpo = textoLimpo;
    }

    public String encoda(){

        Assert.hasLength(textoLimpo, "senha nao pode ser em branco");
        Assert.isTrue(textoLimpo.length()>=6,"senha tem que ter no mínimo 6 caracteres");

        return new BCryptPasswordEncoder().encode(this.textoLimpo);
    }

}
