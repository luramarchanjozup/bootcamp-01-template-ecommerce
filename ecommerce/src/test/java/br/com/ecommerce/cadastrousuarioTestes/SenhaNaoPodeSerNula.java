package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class SenhaNaoPodeSerNula {


    @Test(expected = IllegalArgumentException.class)
    public void senhaNaoPodeSerBrancaOuNula(){

        Usuario usuario = new Usuario("teste@email.com", new SenhaLimpa(""));

        Assert.assertTrue(usuario == null);

    }

}
