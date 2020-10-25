package br.com.ecommerce.finalizacompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProcessadorCompraController {


    @Autowired
    private EntityManager entityManager;


    @Autowired
    private ProcessaCompra processaCompra;



    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> pagamentoPaypal(@PathVariable Long id,
                                             @RequestBody @Valid RetornoPaypalRequest retornoPaypalRequest){


        String pagamentoProcessado = processaCompra
                .processa(id, retornoPaypalRequest, entityManager);

        return ResponseEntity
                .ok(pagamentoProcessado);


    }



    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> pagamentoPagseguro(@PathVariable Long id,
                                                @RequestBody @Valid RetornoPagseguroRequest retornoPagseguroRequest){

        String pagamentoProcessado = processaCompra
                .processa(id, retornoPagseguroRequest, entityManager);

        return ResponseEntity.ok(pagamentoProcessado);

    }



}
