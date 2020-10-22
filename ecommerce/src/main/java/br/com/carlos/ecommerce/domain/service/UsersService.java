package br.com.carlos.ecommerce.domain.service;

import br.com.carlos.ecommerce.domain.entity.Usuario;
import br.com.carlos.ecommerce.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return toUserDetails(usuario.get());
        }

    private UserDetails toUserDetails(Usuario usuario){
        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .authorities(new ArrayList<>())
                .build();
    }
}