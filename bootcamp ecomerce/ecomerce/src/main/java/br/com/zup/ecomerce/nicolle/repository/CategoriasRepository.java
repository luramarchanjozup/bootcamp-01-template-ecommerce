package br.com.zup.ecomerce.nicolle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import br.com.zup.ecomerce.nicolle.model.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long> {
	
	Optional<Categoria> findByNome(String nome);

	

}
