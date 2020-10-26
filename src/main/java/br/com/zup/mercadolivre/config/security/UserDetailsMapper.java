package br.com.zup.mercadolivre.config.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeASystemUser);
}
