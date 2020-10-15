package br.com.ecommerce.cadastrousuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private String textoLimpo;

    public SenhaLimpa(String textoLimpo) {
        this.textoLimpo = textoLimpo;
    }

    public String encoda(){
        return new BCryptPasswordEncoder().encode(this.textoLimpo);
    }

}
