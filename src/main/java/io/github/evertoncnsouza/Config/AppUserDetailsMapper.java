package io.github.evertoncnsouza.Config;

import io.github.evertoncnsouza.domain.entity.Usuario;
import io.github.evertoncnsouza.domain.repository.UserDetailsMapper;
import io.github.evertoncnsouza.seguranca.UsuarioLogado;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

//3 PCI's
@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }
}
