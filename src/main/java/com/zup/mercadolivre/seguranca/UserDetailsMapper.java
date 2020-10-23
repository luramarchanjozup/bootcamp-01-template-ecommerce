package com.zup.mercadolivre.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeASystemUser);
}
