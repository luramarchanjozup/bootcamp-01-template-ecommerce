package com.zup.mercadolivre.services;

import com.zup.mercadolivre.config.security.UserSS;
import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userObj = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSS(userObj.getId(), userObj.getEmail(), userObj.getPassword(), userObj.getProfiles());
    }
    
}
