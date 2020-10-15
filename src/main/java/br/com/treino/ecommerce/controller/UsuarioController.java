package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Usuario;
import br.com.treino.ecommerce.request.NovoUsuarioRequest;
import br.com.treino.ecommerce.validations.EmailDuplicadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private EmailDuplicadoValidator emailDuplicadoValidator; //1

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(emailDuplicadoValidator);
    }

    @PostMapping(value = "/usuarios")
    @Transactional
    public ResponseEntity novoUsuario(@RequestBody @Valid NovoUsuarioRequest request){ //2
        Usuario novoUsuario = request.toModel(); //2
        entityManager.persist(novoUsuario);
        return ResponseEntity.ok().build();
    }
    

}
