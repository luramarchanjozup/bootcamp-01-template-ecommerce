package br.com.zup.ecomerce.nicolle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.ecomerce.nicolle.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByLogin(String login);

}
