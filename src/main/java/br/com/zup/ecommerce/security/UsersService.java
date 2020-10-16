package br.com.zup.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Contagem de carga intrínseca da classe: 2
 */

@Service
public class UsersService implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;
    @Value("${security.username-query}")
    private String query;
    @Autowired
    //1
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<?> objects = manager
                .createQuery(query)
                .setParameter("username",username)
                .getResultList();

        Assert.isTrue(objects.size() <= 1, "[BUG] mais de um autenticável tem o mesmo username. " + username);

        //1
        if(objects.isEmpty()) {
            throw new UsernameNotFoundException("Nao foi possivel encontrar usuário com login: " + username);
        }

        return userDetailsMapper.map(objects.get(0));
    }
}
