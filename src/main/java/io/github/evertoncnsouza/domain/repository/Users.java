package io.github.evertoncnsouza.domain.repository;

import io.github.evertoncnsouza.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import javax.validation.constraints.Email;
import java.util.Optional;

public interface Users extends CrudRepository<User, Long> {

   public Optional<User> findByEmail(@Email String email);
}
