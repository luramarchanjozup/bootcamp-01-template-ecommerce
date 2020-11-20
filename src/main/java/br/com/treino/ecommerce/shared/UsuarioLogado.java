package br.com.treino.ecommerce.shared;

import br.com.treino.ecommerce.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class UsuarioLogado implements UserDetails {

    private Usuario usuario;
    private User springUserDetails;

    public UsuarioLogado(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
        this.springUserDetails = new User(usuario.getEmail(), usuario.getSenha(), List.of());
    }

    public Collection<GrantedAuthority> getAuthorities(){
        return springUserDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return springUserDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return springUserDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return springUserDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return springUserDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return springUserDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return springUserDetails.isEnabled();
    }

    public Usuario get(){
        return this.usuario;
    }

}
