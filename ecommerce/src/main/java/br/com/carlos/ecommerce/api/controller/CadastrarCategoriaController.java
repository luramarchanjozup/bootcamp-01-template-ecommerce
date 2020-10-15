package br.com.carlos.ecommerce.api.controller;


import br.com.carlos.ecommerce.api.dto.RequestCategoriaDto;
import br.com.carlos.ecommerce.api.dto.ResponseCategoriaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class CadastrarCategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    @PostMapping(value="categorias")
    public ResponseCategoriaDto adicionar(@Valid @RequestBody RequestCategoriaDto request ) {
        var categoria = request.toEntity(manager);
        manager.persist(categoria);
        return mapper.map(categoria, ResponseCategoriaDto.class);
    }
    
    
}
