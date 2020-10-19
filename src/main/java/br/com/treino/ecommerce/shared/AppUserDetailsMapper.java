package br.com.treino.ecommerce.shared;

import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.shared.security.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }
}
