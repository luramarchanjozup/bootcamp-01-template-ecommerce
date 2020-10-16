package br.com.ecommerce.seguranca;

import br.com.ecommerce.cadastrousuario.Usuario;
import br.com.ecommerce.cadastrousuario.UsuarioRepository;
import br.com.ecommerce.usuariologado.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscaUsuarioPeloEmail implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        if (!usuario.isPresent()){

            throw new UsernameNotFoundException(
                    "Não foi possível encontrar usuário com email: "
                            + username);

        }

        return userDetailsMapper.map(usuario.get());

    }
}
