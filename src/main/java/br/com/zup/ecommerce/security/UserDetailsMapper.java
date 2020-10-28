package br.com.zup.ecommerce.security;

import br.com.zup.ecommerce.entities.usuario.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Contagem de carga intrínseca da classe: 1
 */

@Configuration
public class UserDetailsMapper {

    /**
     *
     * @param shouldBeASystemUser um objeto que deveria representar seu usuário logado
     */
    public UserDetails map(Object shouldBeASystemUser) {
        //1
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }
}
