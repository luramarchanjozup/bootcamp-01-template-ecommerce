package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class TentativaDePagamentoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired //1
    private TentativaDePagamentoService service;

    @PostMapping("/{idCompra}")
    @Transactional
    public ResponseEntity<String> tentativaDePagamento(@PathVariable UUID idCompra,
                                                       @RequestBody @Valid TentativaDePagamentoDTO dto, //2
                                                       UriComponentsBuilder uri){

        //3
        Compra compra = entityManager.find(Compra.class, idCompra);

        //4
        if(compra == null){
            return ResponseEntity.status(404).body("A compra informada não está cadastrada");
        }

        //5
        TentativaDePagamento tentativaDePagamento = dto.toModel();

        compra.adicionarTentativaDePagamento(tentativaDePagamento);

        service.processaTentativaDePagamento(compra, tentativaDePagamento, uri.toUriString());

        entityManager.merge(compra);
        return ResponseEntity.ok(compra.toString());
    }
}
