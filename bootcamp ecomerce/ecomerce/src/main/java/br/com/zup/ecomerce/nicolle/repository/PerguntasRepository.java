package br.com.zup.ecomerce.nicolle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.ecomerce.nicolle.model.Pergunta;

@Repository
public interface PerguntasRepository extends JpaRepository<Pergunta, Long>{

}
