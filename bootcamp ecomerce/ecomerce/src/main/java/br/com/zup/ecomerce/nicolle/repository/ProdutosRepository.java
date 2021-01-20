package br.com.zup.ecomerce.nicolle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.ecomerce.nicolle.model.Produto;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {
	
	

}
