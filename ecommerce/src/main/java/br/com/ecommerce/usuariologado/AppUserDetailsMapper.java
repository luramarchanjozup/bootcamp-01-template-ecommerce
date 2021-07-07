package br.com.ecommerce.usuariologado;

import br.com.ecommerce.cadastrousuario.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

    @Override
    public UserDetails map(Object shouldBeASystemUser) {

        Assert.isInstanceOf(Usuario.class, shouldBeASystemUser);

        return new UsuarioLogado((Usuario) shouldBeASystemUser);

    }
}
