package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/pagseguro/{idCompra}")
    @Transactional
    public ResponseEntity tentativaDePagamentoPagseguro(@PathVariable UUID idCompra,
                                                       @RequestBody @Valid PagSeguroDTO dto, //2
                                                       UriComponentsBuilder uri){
        return processarTentativaDePagamentoDaCompra(idCompra, dto, uri);
    }

    @PostMapping("/paypal/{idCompra}")
    @Transactional
    public ResponseEntity tentativaDePagamentoPaypal(@PathVariable UUID idCompra,
                                                       @RequestBody @Valid PaypalDTO dto, //3
                                                       UriComponentsBuilder uri){
        return processarTentativaDePagamentoDaCompra(idCompra, dto, uri);
    }

    private ResponseEntity processarTentativaDePagamentoDaCompra(UUID idCompra,
                                                                 TentativaDePagamentoInterface tentativaDePagamentoInterface, //4
                                                                 UriComponentsBuilder uri){

        //5
        Compra compra = entityManager.find(Compra.class, idCompra);

        //6
        if(compra == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A compra informada não está cadastrada");
        }

        service.processaTentativaDePagamento(compra, tentativaDePagamentoInterface, uri.toUriString());

        return ResponseEntity.ok(compra.toString());
    }




}
