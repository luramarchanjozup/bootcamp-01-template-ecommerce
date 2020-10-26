package br.com.carlos.ecommerce.sistemaexterno;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemaExternoController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NotaFiscalRequestDto request) {
        System.out.println("criando nota "+request.toString());
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingRequestDto request) {
        System.out.println("criando ranking"+request.toString());
    }
}
