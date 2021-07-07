package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class SenhaDeveTerMais6Caracteres {


    @Test(expected = IllegalArgumentException.class)
    public void senhaDeveSerMaiorQueSeisCaracteres(){

        Usuario usuario = new Usuario("teste@email.com", new SenhaLimpa("teste"));

        Assert.assertTrue(usuario == null);

    }


}
