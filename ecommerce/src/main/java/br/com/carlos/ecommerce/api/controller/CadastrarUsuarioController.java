package br.com.carlos.ecommerce.api.controller;

import br.com.carlos.ecommerce.api.dto.RequestUsuarioDto;
import br.com.carlos.ecommerce.domain.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastrarUsuarioController {

    @Autowired
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody RequestUsuarioDto request) {
    var uauario = mapper.map(request, Usuario.class);
    manager.persist(uauario);
    }
    
}
