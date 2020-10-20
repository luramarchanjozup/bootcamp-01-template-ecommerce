package com.github.marcoscoutozup.ecommerce.outrossistemas;

import com.github.marcoscoutozup.ecommerce.compra.comunicacao.OutrosSistemasDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemas {

    @PostMapping("/notafiscal")
    public void enviarNotaFiscal(@RequestBody OutrosSistemasDTO dto){
        System.out.println("************");
        System.out.println("Enviando nota fiscal...");
        System.out.println("Id compra: " + dto.getIdCompra());
        System.out.println("Id comprador: "+ dto.getIdUsuario());
        System.out.println("************");
    }

    @PostMapping("/rankingvendedores")
    public void enviarRankingDeVendedores(@RequestBody OutrosSistemasDTO dto){
        System.out.println("************");
        System.out.println("Enviando ranking dos vendedores...");
        System.out.println("Id compra: " + dto.getIdCompra());
        System.out.println("Id vendedor: "+ dto.getIdUsuario());
        System.out.println("************");
    }

}
