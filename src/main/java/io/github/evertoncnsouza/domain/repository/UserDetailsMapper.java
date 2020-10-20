package io.github.evertoncnsouza.domain.repository;


import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);
}
