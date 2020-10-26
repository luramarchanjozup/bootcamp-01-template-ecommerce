package br.com.zup.mercadolivre.config.security;


import br.com.zup.mercadolivre.config.security.UserDetailsMapper;
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

@Service
public class UsersService implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Value("${security.username-query}")
    private String query;

    private UserDetailsMapper userDetailsMapper;

    @Autowired
    public UsersService(UserDetailsMapper userDetailsMapper) {
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        List<?> objects = manager.createQuery(query)
                .setParameter("login", login).getResultList();
        Assert.isTrue(objects.size() <= 1,
                "[BUG] mais de um autenticável tem o mesmo username. "
                        + login);

        if (objects.isEmpty()) {
            throw new UsernameNotFoundException(
                    "Não foi possível encontrar usuário com email: "
                            + login);
        }

        return userDetailsMapper.map(objects.get(0));
    }

}