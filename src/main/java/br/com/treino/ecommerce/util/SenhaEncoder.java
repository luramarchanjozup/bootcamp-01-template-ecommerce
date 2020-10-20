package br.com.treino.ecommerce.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class SenhaEncoder {

    private @NotBlank String senhaLimpa;

    public SenhaEncoder(String senhaLimpa){
        this.senhaLimpa = senhaLimpa;
    }

    public String encodar(){
        return new BCryptPasswordEncoder()
                .encode(this.senhaLimpa);
    }

}
