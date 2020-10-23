package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class SenhaDeveSerGuardadaComHash {

    @Test
    public void senhaDeveEstarCriptografada(){

        Usuario usuario = new Usuario("teste@email.com", new SenhaLimpa("teste123"));

        Assert.assertTrue(usuario.getSenha() != "teste123");

    }


}
