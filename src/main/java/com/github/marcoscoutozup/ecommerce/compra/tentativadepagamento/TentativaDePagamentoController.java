package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.Compra;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.ComunicacaoComSistemas;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.OutrosSistemasDTO;
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
    private ComunicacaoComSistemas comunicacaoComSistemas;

    @PostMapping("/{idCompra}")
    @Transactional                                                                              //2
    public ResponseEntity<String> tentativaDePagamento(@PathVariable UUID idCompra, @RequestBody @Valid TentativaDePagamentoDTO dto, UriComponentsBuilder uri){

        //3
        Compra compra = entityManager.find(Compra.class, idCompra);

        //4
        if(compra == null){
            return ResponseEntity.status(404).body("Compra não encontrada");
        }

        //5
        TentativaDePagamento tentativaDePagamento = dto.toModel();
        compra.adicionarTentativaDePagamento(tentativaDePagamento);

        //6
        if(!tentativaDePagamento.transacaoFoiUmSucesso()){
            System.out.println(compra.prepararFalhaDeCompraParaEmailDoComprador(uri.toUriString()));
            return ResponseEntity.status(400).body("Houve falha na transação na operadora " + compra.getGatewayDePagamento());
        }

                                                        //7
        comunicacaoComSistemas.enviarNotaFiscal(new OutrosSistemasDTO(compra.getId(), compra.retornarIdComprador()));
        comunicacaoComSistemas.enviarRankingVendedores(new OutrosSistemasDTO(compra.getId(), compra.retornarIdVendedor()));
        System.out.println(compra.prepararDetalhesDeCompraParaEmailDoComprador());

        entityManager.merge(compra);
        return ResponseEntity.ok(compra.toString());
    }
}
