package com.zup.mercadolivre.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {
    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest request) throws  InterruptedException {
        System.out.println("Criando nota" + request);
        Thread.sleep(150);
    }
}
