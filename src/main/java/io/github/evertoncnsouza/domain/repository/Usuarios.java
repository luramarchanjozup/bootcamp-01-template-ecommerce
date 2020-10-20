package io.github.evertoncnsouza.domain.repository;

import io.github.evertoncnsouza.domain.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface Usuarios extends CrudRepository<Usuario, Long> {

   public Optional<Usuario> findByEmail(@Email String email);
}
