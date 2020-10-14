package br.com.zup.ecommerce.entities.usuario;

import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Contagem de carga intrínseca da classe: 2
 */

public class SenhaTratada {

    private String senha;

    public SenhaTratada(@NotBlank @Size(min = 6) String senha) {

        Assert.notNull(senha, "A senha não pode ser vazia");
        Assert.isTrue(senha.length() >= 6, "A senha deve ter tamanho igual ou maior que 6");

        this.senha = this.criptografarSenha(senha);
    }

    public String getSenha() {
        return senha;
    }

    private String criptografarSenha(String senha) {
        MessageDigest algotimo;
        byte[] sDigest = new byte[0];

        //2
        try {
            algotimo = MessageDigest.getInstance("SHA-256");
            sDigest = algotimo.digest(senha.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder hexStringSenha = new StringBuilder();
        //1
        for (byte b : sDigest) {
            hexStringSenha.append(String.format("%02X", 0xFF & b));
        }

        return hexStringSenha.toString();
    }
}
