package com.github.marcoscoutozup.ecommerce.seguranca;

import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", email)
                .getResultList().stream().findFirst();

        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return toUserDetails(usuario.get());
    }

    private UserDetails toUserDetails(Usuario usuario){
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(new ArrayList<>())
                .build();
    }
}
