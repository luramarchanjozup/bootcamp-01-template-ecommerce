package br.com.ecommerce.cadastrousuarioTestes;

import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class InstanteDeCriacaoNaoPodeSerNulo {

    @Test
    public void instanteDeCriacaoNaoPodeSerNuloNemNoFuturo(){

        Usuario usuario = new Usuario("teste@email.com", new SenhaLimpa("teste123"));

        Assert.assertNotNull(usuario.getInstanteCadastro());

    }
}
