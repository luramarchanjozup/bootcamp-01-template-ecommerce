package br.com.ecommerce.usuariologado;

import br.com.ecommerce.cadastrousuario.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

    @Override
    public UserDetails map(Object shouldBeASystemUser) {

        return new UsuarioLogado((Usuario)shouldBeASystemUser);

    }

}
